package com.training.nicklos.vmcolorlist.di

import android.arch.persistence.room.Room
import android.content.Context
import com.training.nicklos.vmcolorlist.db.ColorDao
import com.training.nicklos.vmcolorlist.db.ColorDatabase
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module to provide dependency on an application level
 */
@Module(includes = arrayOf(ViewModelModule::class))
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesAppContext() = context

    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): ColorDatabase =
            Room.databaseBuilder(context, ColorDatabase::class.java, "color-db").allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun providesColorDao(database: ColorDatabase) = database.colorDao()

    @Provides
    @Singleton
    fun providesColorRepository(colorDao: ColorDao) = ColorRepository(colorDao)

}