package com.training.nicklos.vmcolorlist.di

import android.app.Application
import android.arch.persistence.room.Room
import android.support.test.espresso.idling.CountingIdlingResource
import com.training.nicklos.vmcolorlist.db.ColorDao
import com.training.nicklos.vmcolorlist.db.ColorDatabase
import com.training.nicklos.vmcolorlist.db.IdlingColorDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Version of [DatabaseModule] for instrumented tests.
 *
 * Provides an in-memory database to make tests completely independent.
 * The data will be wiped every time the app is restarted.
 *
 * Provides a dao that handles idling resources.
 */
@Module
class TestDatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(app: Application): ColorDatabase =
            Room.inMemoryDatabaseBuilder(app, ColorDatabase::class.java).build()

    @Provides
    @Singleton
    fun providesColorDao(database: ColorDatabase, idlingRes: CountingIdlingResource): ColorDao
            = IdlingColorDao(database, idlingRes)
}