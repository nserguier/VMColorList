package com.training.nicklos.vmcolorlist.db

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.training.nicklos.vmcolorlist.util.TestUtil
import org.junit.Assert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation tests for the implementation of [ColorDao]
 * Needs to run on a device
 */
@RunWith(AndroidJUnit4::class)
class ColorDaoTest {

    private lateinit var colorDb: ColorDatabase
    private lateinit var colorDao: ColorDao

    @Before
    fun initDb() {
        colorDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(),
                ColorDatabase::class.java).build()
        colorDao = colorDb.colorDao()
    }

    @After
    fun closeDb() {
        colorDb.close()
    }

    @Test
    fun testInsertAndFind() {
        val color = TestUtil.createColor(1)
        val color2 = TestUtil.createColor(2)
        colorDao.insertColor(color)
        colorDao.insertColor(color2)

        Assert.assertEquals(color, colorDao.findColorById(1))
        Assert.assertEquals(color2, colorDao.findColorById(2))
    }

    @Test
    fun testInsertSameId() {
        val color = TestUtil.createColor(1)
        val color2 = TestUtil.createColor(1)
        colorDao.insertColor(color)
        colorDao.insertColor(color2)

        //It should replace when ID conflicts (are the same)
        Assert.assertEquals(color2, colorDao.findColorById(1))
    }

    @Test
    fun testUpdate() {
        val color = TestUtil.createColor(1)
        colorDao.insertColor(color)
        color.red = 25
        colorDao.updateColor(color)

        Assert.assertEquals(25, colorDao.findColorById(color.id).red)
    }

    @Test
    fun testDelete() {
        val color = TestUtil.createColor(1)
        colorDao.insertColor(color)
        colorDao.deleteColor(color)
        Assert.assertNull(colorDao.findColorById(color.id))
    }
}