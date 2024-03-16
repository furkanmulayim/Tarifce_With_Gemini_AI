package com.furkanmulayim.tarifce.data.db.shoping_list

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.furkanmulayim.tarifce.data.model.Shopliste

@Database(entities = [Shopliste::class], version = 1)
abstract class ShoppingListDatabase : RoomDatabase() {
    abstract fun shopListDao(): ShoppingListDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingListDatabase? = null

        fun getInstance(context: Context): ShoppingListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, ShoppingListDatabase::class.java, "shopliste"
                ).createFromAsset("shopliste.sqlite").build()
                INSTANCE = instance
                instance
            }
        }


    }
}
