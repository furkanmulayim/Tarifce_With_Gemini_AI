package com.furkanmulayim.tarifce.data.database.shoplist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.furkanmulayim.tarifce.data.model.Material

@Database(entities = [Material::class], version = 1)
abstract class ShoppingListDatabase : RoomDatabase() {
    abstract fun shopListDao(): ShoppingListDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingListDatabase? = null

        fun getInstance(context: Context): ShoppingListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, ShoppingListDatabase::class.java, "material"
                ).createFromAsset("material.sqlite").build()
                INSTANCE = instance
                instance
            }
        }


    }
}
