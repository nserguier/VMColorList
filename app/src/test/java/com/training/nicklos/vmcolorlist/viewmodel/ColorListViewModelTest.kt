package com.training.nicklos.vmcolorlist.viewmodel

import com.nhaarman.mockito_kotlin.verify
import com.training.nicklos.vmcolorlist.AppExecutors
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import com.training.nicklos.vmcolorlist.viewmodel.ColorListViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

/**
 * Unit tests for the implementation of [ColorListViewModel]
 */
@RunWith(JUnit4::class)
class ColorListViewModelTest {

    @Mock
    private lateinit var colorRepository: ColorRepository

    private lateinit var syncAppExecutor: AppExecutors

    private lateinit var viewModel: ColorListViewModel

    @Before
    fun setup() {
        // inject the mocks in the test
        MockitoAnnotations.initMocks(this)

        //Create a fake executor to execute everything synchronously (on the testing thread)
        val syncExecutor = Executor { it.run() }
        syncAppExecutor = AppExecutors(syncExecutor, syncExecutor, syncExecutor)

        //Create the view model under test with those dependencies
        viewModel = ColorListViewModel(colorRepository, syncAppExecutor)
    }

    @Test
    fun testAddColor() {
        viewModel.addColor()
        verify(colorRepository).addRandomColor()
    }

    @Test
    fun testDeleteColor() {
        val color = Color(0, 0, 0)
        viewModel.deleteColor(color)
        verify(colorRepository).deleteColor(color)
    }
}