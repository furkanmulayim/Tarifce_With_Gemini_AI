package com.furkanmulayim.tarifce.data.db.food

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.furkanmulayim.tarifce.data.model.Food

@Database(entities = [Food::class], version = 4)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, FoodDatabase::class.java, "foods_database"
                ).addMigrations(MIGRATION_3_4).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Eski tabloyu sil
                database.execSQL("DROP TABLE IF EXISTS Food")

                // Yeni tabloyu oluştur
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS NewFoodTable (" + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "name TEXT, " +
                            // Diğer sütunlar buraya eklenebilir
                            ")"
                )
            }
        }
    }
}
