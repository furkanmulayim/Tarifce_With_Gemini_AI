package com.furkanmulayim.tarifce.data.database.shoplist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.furkanmulayim.tarifce.data.model.Material

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM material")
    fun allList(): List<Material>

    @Insert
    fun addList(material: Material)

    @Query("DELETE FROM material")
    fun deleteAll()

    @Query("DELETE FROM material WHERE name = :name")
    fun deleteMaterial(name: String)

    @Query("SELECT * FROM material WHERE name = :name")
    fun listGet(name: String): Material
}