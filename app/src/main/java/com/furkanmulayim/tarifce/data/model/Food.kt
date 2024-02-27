package com.furkanmulayim.tarifce.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("id") val id: Int,
    @ColumnInfo("image") @SerializedName("image") val image: String, //degisecek
    @ColumnInfo("name") @SerializedName("name") val name: String,
    @ColumnInfo("category") @SerializedName("category") val category: String,
    @ColumnInfo("stars") @SerializedName("stars") val stars: String,
    @ColumnInfo("trend") @SerializedName("trend") val trend: String,
    @ColumnInfo("duration") @SerializedName("duration") var duration: String,
    @ColumnInfo("calorie") @SerializedName("calorie") var calorie: String,
    @ColumnInfo("person") @SerializedName("person") var person: String,
    @ColumnInfo("level") @SerializedName("level") var level: String,
    @ColumnInfo("hastags") @SerializedName("hastags") var hastags: String,
    @ColumnInfo("specifics") @SerializedName("specific") var specific: String
) : Serializable