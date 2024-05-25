package com.furkanmulayim.tarifce.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.model.Food
import com.google.firebase.firestore.CollectionReference

class FoodDataSource(private val collectionFood: CollectionReference) {

    private var foodList = MutableLiveData<ArrayList<Food>>()

    fun getData(): MutableLiveData<ArrayList<Food>> {
        collectionFood.addSnapshotListener { value, error ->
            if (value != null) {
                val list = ArrayList<Food>()
                for (i in value.documents) {
                    val food = i.toObject(Food::class.java)
                    if (food != null) {
                        list.add(food)
                    }
                }
                foodList.value = list
            } else {
                Log.e("LOGDF - FoodDataSource", "LİSTE BOŞ GELDİ")
            }

            if (error?.message?.isNotEmpty() == true) {
                error.localizedMessage?.let { Log.e("LOGDF - FoodDataSource", it) }
            }
        }
        return foodList
    }
}