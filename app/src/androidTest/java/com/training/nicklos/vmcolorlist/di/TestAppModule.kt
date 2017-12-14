package com.training.nicklos.vmcolorlist.di

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import com.training.nicklos.vmcolorlist.db.ColorDao
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Version of [AppModule] for instrumented tests.
 */
@Module(includes = arrayOf(ViewModelModule::class, TestDatabaseModule::class, TestColorListObserverModule::class,
        ExecutorModule::class))
class TestAppModule {

    @Provides
    @Singleton
    fun providesColorRepository(colorDao: ColorDao) = ColorRepository(colorDao)

    @Singleton
    @Provides
    fun providesUiDevice(): UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
}