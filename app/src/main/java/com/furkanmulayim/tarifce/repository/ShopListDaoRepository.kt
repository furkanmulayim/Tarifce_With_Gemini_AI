package com.furkanmulayim.tarifce.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.db.shoping_list.ShoppingListDao
import com.furkanmulayim.tarifce.data.model.Shopliste
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopListDaoRepository(private var sldao: ShoppingListDao) {


    private var shopList: MutableLiveData<List<Shopliste>> = MutableLiveData()
    fun shopListPostViewModel(): MutableLiveData<List<Shopliste>> {
        return shopList
    }

    fun getAllFoods() {
        CoroutineScope(Dispatchers.IO).launch {
            shopList.postValue(sldao.allList())
        }
    }

    fun updateMaterial(
        id: Int,
        issold: Int,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            sldao.updateItem(id, issold)
        }
    }


    fun saveFoods(
        id: Int,
        image: String,
        name: String,
        weight: String,
        explain: String,
        issold: Int,
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            val newFood = Shopliste(
                id = id,
                image = image,
                name = name,
                weight = weight,
                explain = explain,
                issold = issold
            )
            withContext(Dispatchers.IO) {
                sldao.addList(newFood)
            }
        }
    }


    fun deleteMaterial(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            sldao.deleteMaterial(id)
        }
    }
}