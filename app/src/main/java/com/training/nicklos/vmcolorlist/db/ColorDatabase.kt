package com.training.nicklos.vmcolorlist.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.training.nicklos.vmcolorlist.model.Color

/**
 * Room database to store the colors
 */
@Database(entities = arrayOf(Color::class), version = 1, exportSchema = false)
abstract class ColorDatabase : RoomDatabase() {

    abstract fun colorDao(): ColorDao
}