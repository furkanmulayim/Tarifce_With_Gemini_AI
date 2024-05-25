package com.furkanmulayim.tarifce.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryData(
    val id: String? = "",
    val materials: List<Material>? = null
) : Parcelable

@Parcelize
@Entity(tableName = "material")
data class Material(
    @ColumnInfo("name") var name: String? = "",
    @ColumnInfo("url") var url: String? = ""
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}