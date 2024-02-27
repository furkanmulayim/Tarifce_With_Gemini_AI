package com.furkanmulayim.tarifce.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id:Int,
    @ColumnInfo("image") val image: String, //degisecek
    @ColumnInfo("name") val name: String,
    @ColumnInfo("category") val category: String,
    @ColumnInfo("stars") val stars: Double,
    @ColumnInfo("trend") val trend:Boolean,
    @ColumnInfo("duration") var duration: String,
    @ColumnInfo("calorie")  var calorie: String,
    @ColumnInfo("person") var person: String,
    @ColumnInfo("level") var level: String,
    @ColumnInfo("hastags") var hastags: String,
    @ColumnInfo("specifics") var specific: String
)