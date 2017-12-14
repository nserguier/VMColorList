package com.training.nicklos.vmcolorlist.di

import com.training.nicklos.vmcolorlist.ui.colorlist.ColorListObserver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module to provide a [ColorListObserver]
 */
@Module
class ColorListObserverModule {

    @Provides
    @Singleton
    fun providesColorListObserver() = ColorListObserver()
}