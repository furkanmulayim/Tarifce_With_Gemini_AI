package com.furkanmulayim.tarifce.data.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.database.shoplist.ShoppingListDao
import com.furkanmulayim.tarifce.data.model.Material
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopListDaoRepository(private var sldao: ShoppingListDao) {


    private var shopList: MutableLiveData<List<Material>> = MutableLiveData()
    fun shopListPostViewModel(): MutableLiveData<List<Material>> {
        return shopList
    }

    fun getAllFoods() {
        CoroutineScope(Dispatchers.IO).launch {
            shopList.postValue(sldao.allList())
        }
    }

    fun saveFoods(
        material: Material
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            sldao.addList(material)
        }
    }


    fun deleteMaterial(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            sldao.deleteMaterial(id)
        }
    }

    fun allMetarialDelete() {
        CoroutineScope(Dispatchers.IO).launch {
            sldao.deleteAll()
        }
    }
}