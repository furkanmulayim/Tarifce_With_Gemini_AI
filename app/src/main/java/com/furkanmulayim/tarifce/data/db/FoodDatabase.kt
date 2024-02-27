package com.furkanmulayim.tarifce.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.furkanmulayim.tarifce.domain.model.Food

@Database(entities = [Food::class], version = 1)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        var INSTANCE: FoodDatabase? = null
        fun databaseAccess(context: Context): FoodDatabase? {
            if (INSTANCE == null) {
                synchronized(FoodDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, FoodDatabase::class.java, "food.sqlite"
                    ).createFromAsset("food.sqlite").build()

                }
            }
            return INSTANCE
        }

    }
}