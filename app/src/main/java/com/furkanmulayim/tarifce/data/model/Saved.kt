package com.furkanmulayim.tarifce.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "saved")
data class Saved(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("id") @NotNull val id: Int,
    @ColumnInfo("image") @NotNull val image: String,
    @ColumnInfo("name") @NotNull val name: String,
    @ColumnInfo("category") @NotNull val category: String,
    @ColumnInfo("stars") @NotNull val stars: String,
    @ColumnInfo("trend") @NotNull val trend: String,
    @ColumnInfo("duration") @NotNull var duration: String,
    @ColumnInfo("calorie") @NotNull var calorie: String,
    @ColumnInfo("person") @NotNull var person: String,
    @ColumnInfo("level") @NotNull var level: String,
    @ColumnInfo("hastags") @NotNull var hastags: String,
    @ColumnInfo("specifics") @NotNull var specific: String
)