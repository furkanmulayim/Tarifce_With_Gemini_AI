package com.furkanmulayim.tarifce.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.furkanmulayim.tarifce.data.model.Food

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    suspend fun allFoods(): List<Food>

    @Insert
    suspend fun addFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)

    @Query("SELECT * FROM food WHERE name = :name")
    suspend fun foodGet(name: String): Food
}