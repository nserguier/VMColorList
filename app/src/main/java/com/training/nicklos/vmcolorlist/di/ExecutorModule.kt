package com.training.nicklos.vmcolorlist.di

import com.training.nicklos.vmcolorlist.AppExecutors
import com.training.nicklos.vmcolorlist.MainThreadExecutor
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

/**
 * Provide the app with executors
 */
@Module
class ExecutorModule {

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