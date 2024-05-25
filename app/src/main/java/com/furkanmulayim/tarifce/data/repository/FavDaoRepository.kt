package com.furkanmulayim.tarifce.data.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.database.foods.FavDao
import com.furkanmulayim.tarifce.data.model.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavDaoRepository(private var productDao: FavDao) {

    private var productList: MutableLiveData<List<Food>> = MutableLiveData()
    private var product: MutableLiveData<Food> = MutableLiveData()

    fun getLikeDatas(): MutableLiveData<List<Food>> {
        getAll()
        return productList
    }

    fun getSingleLikeData(id: Int): MutableLiveData<Food> {
        getSingle(id)
        return product
    }

    fun saveLikeData(product: Food) {
        saveProduct(product)
    }

    fun deleteSingleData(id: Int) {
        deleteSingle(id)
    }


    private fun getAll() {
        CoroutineScope(Dispatchers.IO).launch {
            productList.postValue(productDao.getAllProducts())
        }
    }

    private fun getSingle(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            product.postValue(productDao.getProduct(id))
        }
    }

    private fun saveProduct(product: Food) {
        CoroutineScope(Dispatchers.IO).launch {
            val uuid = productDao.insertFav(product)
            product.uuid = uuid[0].toInt()
        }
    }

    private fun deleteSingle(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.deleteSingle(id)
        }
    }
}