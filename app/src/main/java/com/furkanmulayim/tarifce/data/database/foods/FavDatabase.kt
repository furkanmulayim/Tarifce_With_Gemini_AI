package com.furkanmulayim.tarifce.data.database.foods

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.furkanmulayim.tarifce.data.model.Food

@Database(entities = [Food::class], version = 1)
abstract class FavDatabase : RoomDatabase() {

    abstract fun productDao(): FavDao

    companion object {
        @Volatile
        private var instance: FavDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, FavDatabase::class.java, "foods"
        ).build()
    }
}