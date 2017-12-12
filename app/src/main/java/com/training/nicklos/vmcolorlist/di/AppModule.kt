package com.training.nicklos.vmcolorlist.di

import com.training.nicklos.vmcolorlist.AppExecutors
import com.training.nicklos.vmcolorlist.MainThreadExecutor
import com.training.nicklos.vmcolorlist.db.ColorDao
import com.training.nicklos.vmcolorlist.db.ColorDatabase
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

/**
 * Module to provide dependency on an application level
 */
@Module(includes = arrayOf(ViewModelModule::class, DatabaseModule::class))
class AppModule {

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