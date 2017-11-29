package com.training.nicklos.vmcolorlist.db

import android.arch.paging.LivePagedListProvider
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.support.annotation.WorkerThread
import com.training.nicklos.vmcolorlist.model.Color

/**
 * Data Access Object, handles the communication with the DB
 *
 * [WorkerThread]: All methods should be called from background threads
 */
@Dao
interface ColorDao {

    @Query("SELECT * FROM color")
    fun getAllColors(): LivePagedListProvider<Int, Color>

    @Query("SELECT * FROM color WHERE id = :id")
    fun findColorById(id: Long): Color

    @Insert(onConflict = REPLACE)
    fun insertColor(color: Color)

    @Update(onConflict = REPLACE)
    fun updateColor(color: Color)

    @Delete
    fun deleteColor(color: Color)
}