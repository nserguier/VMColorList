package com.training.nicklos.vmcolorlist.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.training.nicklos.vmcolorlist.viewmodel.ColorEditViewModel
import com.training.nicklos.vmcolorlist.viewmodel.ColorListViewModel
import com.training.nicklos.vmcolorlist.viewmodel.ColorViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Module to provide dependencies to the viewmodel classes
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ColorListViewModel::class)
    internal abstract fun bindColorListViewModel(colorListViewModel: ColorListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ColorEditViewModel::class)
    internal abstract fun bindColorEditViewModel(colorListViewModel: ColorEditViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ColorViewModelFactory): ViewModelProvider.Factory
}