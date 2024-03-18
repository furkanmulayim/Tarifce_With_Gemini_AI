package com.furkanmulayim.tarifce.data.db.saved_food

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.furkanmulayim.tarifce.data.model.Saved

@Dao
interface SavedDao {
    @Query("SELECT * FROM saved")
    suspend fun allList(): List<Saved>

    @Insert
    suspend fun addSaved(saved: Saved)

    @Delete
    suspend fun deleteList(saved: Saved)

    @Query("DELETE FROM saved WHERE id = :id")
    suspend fun deleteMaterial(id: Int)


}