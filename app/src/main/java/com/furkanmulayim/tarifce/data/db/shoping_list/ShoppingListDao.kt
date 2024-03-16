package com.furkanmulayim.tarifce.data.db.shoping_list

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.furkanmulayim.tarifce.data.model.Shopliste

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM shopliste")
    suspend fun allList(): List<Shopliste>

    @Insert
    suspend fun addList(shopliste: Shopliste)

    @Query("UPDATE shopliste SET issold = :issold WHERE id = :id")
    suspend fun updateItem(id: Int, issold: Int)

    @Delete
    suspend fun deleteList(shopliste: Shopliste)

    @Query("DELETE FROM shopliste WHERE id = :id")
    suspend fun deleteMaterial(id: Int)

    @Query("SELECT * FROM shopliste WHERE name = :name")
    suspend fun listGet(name: String): Shopliste
}