package com.furkanmulayim.tarifce.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "foods")
data class Food(
    @ColumnInfo("id") var id: Int? = 0,
    @ColumnInfo("image") var image: String? = "",
    @ColumnInfo("name") var name: String? = "",
    @ColumnInfo("category") var category: String? = "",
    @ColumnInfo("stars") var stars: String? = "",
    @ColumnInfo("trend") var trend: String? = "",
    @ColumnInfo("duration") var duration: String? = "",
    @ColumnInfo("calorie") var calorie: String? = "",
    @ColumnInfo("person") var person: String? = "",
    @ColumnInfo("level") var level: String? = "",
    @ColumnInfo("hastags") var hastags: String? = "",
    @ColumnInfo("specific") var specific: String? = ""
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}