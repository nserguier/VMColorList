package com.training.nicklos.vmcolorlist.di

import android.app.Application
import android.arch.persistence.room.Room
import android.support.test.espresso.idling.CountingIdlingResource
import com.training.nicklos.vmcolorlist.db.ColorDao
import com.training.nicklos.vmcolorlist.db.ColorDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides an in-memory [ColorDatabase] for testing
 * The data will be wiped every time the app is restarted.
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(app: Application): ColorDatabase =
            Room.inMemoryDatabaseBuilder(app, ColorDatabase::class.java).build()

    @Provides
    @Singleton
    fun providesColorDao(database: ColorDatabase): ColorDao = IdlingColorDao(database)
}