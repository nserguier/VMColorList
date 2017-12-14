package com.training.nicklos.vmcolorlist.di

import com.training.nicklos.vmcolorlist.AppExecutors
import com.training.nicklos.vmcolorlist.MainThreadExecutor
import com.training.nicklos.vmcolorlist.db.ColorDao
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

/**
 * Version of [AppModule] for instrumented tests.
 */
@Module(includes = arrayOf(ViewModelModule::class, TestDatabaseModule::class, TestColorListObserverModule::class))
class TestAppModule {
    //TODO instead of copying everything, try extends?
    //Or put the common stuff in dedicated modules

    @Provides
    @Singleton
    fun providesColorRepository(colorDao: ColorDao) = ColorRepository(colorDao)

    @Provides
    @Singleton
    @Named("diskIO")
    fun providesDiskIOExecutor(): Executor = Executors.newSingleThreadExecutor()

    @Provides
    @Singleton
    @Named("networkIO")
    fun providesNetworkIOExecutor(): Executor = Executors.newFixedThreadPool(3)

    @Provides
    @Singleton
    @Named("mainThread")
    fun providesMainThreadExecutor(): Executor = MainThreadExecutor()

    @Provides
    @Singleton
    fun providesAppExecutors(@Named("diskIO") diskIO: Executor,
                             @Named("networkIO") networkIO: Executor,
                             @Named("mainThread") mainThread: Executor) =
            AppExecutors(diskIO, networkIO, mainThread)

}