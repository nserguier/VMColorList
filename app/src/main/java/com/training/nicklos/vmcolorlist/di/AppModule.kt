package com.training.nicklos.vmcolorlist.di

import com.training.nicklos.vmcolorlist.db.ColorDao
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module to provide dependency on an application level
 */
@Module(includes = arrayOf(ViewModelModule::class, DatabaseModule::class, ColorListObserverModule::class,
        ExecutorModule::class))
class AppModule {

    @Provides
    @Singleton
    fun providesColorRepository(colorDao: ColorDao) = ColorRepository(colorDao)
}