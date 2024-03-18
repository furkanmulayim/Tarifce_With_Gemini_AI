package com.furkanmulayim.tarifce.data.db.saved_food

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.furkanmulayim.tarifce.data.model.Saved

@Database(entities = [Saved::class], version = 1)
abstract class SavedDatabase : RoomDatabase() {
    abstract fun shopListDao(): SavedDao

    companion object {
        @Volatile
        private var INSTANCE: SavedDatabase? = null

        fun getInstance(context: Context): SavedDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, SavedDatabase::class.java, "saved"
                ).createFromAsset("saved.sqlite").build()
                INSTANCE = instance
                instance
            }
        }


    }
}
