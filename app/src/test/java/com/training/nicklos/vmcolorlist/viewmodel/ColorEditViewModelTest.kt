package com.training.nicklos.vmcolorlist.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.training.nicklos.vmcolorlist.AppExecutors
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

/**
 * Unit tests for the implementation of [ColorEditViewModel]
 */
@RunWith(JUnit4::class)
class ColorEditViewModelTest {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var colorRepository: ColorRepository

    private lateinit var syncAppExecutor: AppExecutors

    private lateinit var viewModel: ColorEditViewModel

    private val testColor = Color(130, 11, 240).apply { id = 1 }

    @Before
    fun setup() {
        // inject the mocks in the test
        MockitoAnnotations.initMocks(this)

        //Mock color repo methods
        Mockito.`when`(colorRepository.getColorById(1)).thenReturn(testColor)

        //Create a fake executor to execute everything synchronously (on the testing thread)
        val syncExecutor = Executor { it.run() }
        syncAppExecutor = AppExecutors(syncExecutor, syncExecutor, syncExecutor)

        //Create the view model under test with those dependencies
        viewModel = ColorEditViewModel(colorRepository, syncAppExecutor)
    }

    @Test
    fun setIdAfterLoad() {
        viewModel.colorId = 1
        viewModel.getColor()
        //After the color is first set, changing the is won't trigger another load
        //without a call to resetColor
        viewModel.colorId = 2
        Assert.assertEquals(testColor, viewModel.getColor().value)
    }

    @Test
    fun reuseLoadedColor() {
        viewModel.colorId = 1
        //First call should trigger a call to the repo
        viewModel.getColor()
        //Second call should use the existing color, no call to repo
        Assert.assertEquals(testColor, viewModel.getColor().value)
        verify(colorRepository, times(1)).getColorById(1)
    }

    @Test
    fun sendResultToUi() {
        val observer: Observer<Color> = mock()
        viewModel.colorId = 1
        viewModel.getColor().observeForever(observer)
        verify(observer).onChanged(testColor)
    }

    @Test
    fun testResetColor_reloadColor() {
        viewModel.colorId = 1
        viewModel.getColor()
        viewModel.resetColor()
        //Second call after reset should trigger another call to the repo with new Id
        viewModel.colorId = 2
        viewModel.getColor()
        verify(colorRepository).getColorById(1)
        verify(colorRepository).getColorById(2)
    }

    @Test
    fun testOnColorChanged() {
        viewModel.colorId = 1
        viewModel.getColor()
        viewModel.onColorChanged(15, 210, 98)
        val updatedColor = viewModel.getColor().value
        //The color components should be updated
        Assert.assertEquals(Color(15, 210, 98), updatedColor)
        //The color id should remain the same
        Assert.assertEquals(1L, updatedColor?.id)
    }

    @Test
    fun testSaveColor() {
        //Normal series of event to save a color
        viewModel.colorId = 1
        viewModel.getColor()
        viewModel.saveColor()
        verify(colorRepository).updateColor(testColor)
    }

    @Test
    fun noSaveWithoutColor() {
        viewModel.saveColor()
        verifyNoMoreInteractions(colorRepository)
    }

    @Test(expected = IllegalArgumentException::class)
    fun loadColorWithoutId_throwsException() {
        viewModel.getColor()
    }
}