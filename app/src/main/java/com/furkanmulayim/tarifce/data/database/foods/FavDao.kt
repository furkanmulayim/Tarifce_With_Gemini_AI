package com.furkanmulayim.tarifce.data.database.foods

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.furkanmulayim.tarifce.data.model.Food

@Dao
interface FavDao {

    @Insert
    fun instert(vararg product: Food): List<Long>

    @Insert
    fun insertFav(vararg product: Food): List<Long>

    @Query("SELECT * FROM foods")
    fun getAllProducts(): List<Food>

    @Query("SELECT * FROM foods WHERE id = :id")
    fun getProduct(id: Int): Food

    @Query("UPDATE foods SET trend = 1 WHERE id = :id")
    fun updateProductFavorite(id: Int)


    @Query("DELETE FROM foods")
    fun deleteAllProduct()

    @Query("DELETE FROM foods WHERE id = :id")
    fun deleteSingle(id: Int)

}