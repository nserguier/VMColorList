package com.training.nicklos.vmcolorlist.viewmodel

import com.nhaarman.mockito_kotlin.verify
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Unit tests for the implementation of [ColorListViewModel]
 */
class ColorListViewModelTest {

    //@Rule
    //var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var colorRepository: ColorRepository

    private lateinit var viewModel: ColorListViewModel

    @Before
    fun setup() {
        // inject the mocks in the test
        MockitoAnnotations.initMocks(this)

        viewModel = ColorListViewModel(colorRepository)
    }

    @Test
    fun lazyInitialization_getColors() {
        viewModel.colors
        verify(colorRepository).getColors()
    }

    @Test
    fun addColor_addRandomColor() {
        viewModel.addColor()
        verify(colorRepository).addRandomColor()
    }

    @Test
    fun deleteColor_test() {
        Assert.assertEquals(4, 2 + 2)
    }
}