package com.furkanmulayim.tarifce.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "shopliste")
data class Shopliste(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") @NotNull var id: Int = 0,
    @ColumnInfo(name = "name") @NotNull ("name") var name: String,
    @ColumnInfo(name = "image") @NotNull ("image") var image: String,
    @ColumnInfo(name = "weight") @NotNull ("weight") var weight: String,
    @ColumnInfo(name = "explain") @NotNull ("explain") var explain: String,
    @ColumnInfo(name = "issold") @NotNull ("issold") var issold: Int
)
