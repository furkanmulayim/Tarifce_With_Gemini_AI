package com.furkanmulayim.tarifce.data.repo

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.db.shoping_list.ShoppingListDao
import com.furkanmulayim.tarifce.data.model.Shopliste
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopListDaoRepository(var sldao: ShoppingListDao) {


    private var shopList: MutableLiveData<List<Shopliste>> = MutableLiveData()
    fun shopListPostViewModel(): MutableLiveData<List<Shopliste>> {
        return shopList
    }

    fun getAllFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            shopList.value = sldao.allList()
        }
    }


    private var list: MutableLiveData<Shopliste> = MutableLiveData()
    fun shopPostViewModel(): MutableLiveData<Shopliste> {
        return list
    }

    fun getFood(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            list.value = sldao.listGet(name)
        }
    }

    fun updateMaterial(
        id: Int,
        issold: Int,
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            sldao.updateItem(id,issold)
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
            sldao.addList(newFood)
        }
    }


    fun deleteMaterial(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            sldao.deleteMaterial(id)
        }
    }
}