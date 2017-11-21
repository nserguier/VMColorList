package com.training.nicklos.vmcolorlist.di

import dagger.Module
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.training.nicklos.vmcolorlist.viewmodel.ColorListViewModel
import com.training.nicklos.vmcolorlist.viewmodel.ColorViewModelFactory
import dagger.multibindings.IntoMap
import dagger.Binds


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
    abstract fun bindViewModelFactory(factory: ColorViewModelFactory): ViewModelProvider.Factory
}