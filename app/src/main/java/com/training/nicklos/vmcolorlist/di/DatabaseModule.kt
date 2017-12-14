package com.training.nicklos.vmcolorlist.di

import android.app.Application
import android.arch.persistence.room.Room
import com.training.nicklos.vmcolorlist.db.ColorDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module to provide a database and a dao to the app
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(app: Application): ColorDatabase =
            Room.databaseBuilder(app, ColorDatabase::class.java, "color-db").build()

    @Provides
    @Singleton
    fun providesColorDao(database: ColorDatabase) = database.colorDao()

}