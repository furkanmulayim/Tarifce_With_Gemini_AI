package com.furkanmulayim.tarifce.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.model.CategoryData
import com.google.firebase.firestore.CollectionReference

class CategoryDataSource(private val collectionFood: CollectionReference) {

    private var foodList = MutableLiveData<ArrayList<CategoryData>>()

    fun getData(): MutableLiveData<ArrayList<CategoryData>> {
        collectionFood.addSnapshotListener { value, error ->
            if (error != null) {
                Log.e("LOGDF - FoodDataSource", "Hata oluştu", error)
                return@addSnapshotListener
            }

            if (value != null) {
                val list = ArrayList<CategoryData>()
                for (document in value.documents) {
                    val food = document.toObject(CategoryData::class.java)
                    food?.let { list.add(it) }
                }
                foodList.postValue(list)
            } else {
                Log.e("LOGDF - FoodDataSource", "LİSTE BOŞ GELDİ")
            }
        }
        return foodList
    }
}