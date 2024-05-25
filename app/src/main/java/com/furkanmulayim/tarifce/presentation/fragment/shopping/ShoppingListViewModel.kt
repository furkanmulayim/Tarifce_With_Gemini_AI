package com.furkanmulayim.tarifce.presentation.fragment.shopping

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.tarifce.base.BaseViewModel
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.data.repository.ShopListDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    application: Application,
    private var shopListDaoRepository: ShopListDaoRepository
) : BaseViewModel(application) {

    var shopList = MutableLiveData<List<Material>>()


    fun getList() {
        viewModelScope.launch {
            shopListDaoRepository.getAllFoods()
            shopList = shopListDaoRepository.shopListPostViewModel()
        }
    }

    fun deleteItem(id: String) {
        shopListDaoRepository.deleteMaterial(id)
        //deleted item for adapterList
        val currentList = shopList.value.orEmpty().toMutableList()
        val deletedItem = currentList.find { it.name == id }
        deletedItem?.let {
            currentList.remove(it)
            shopList.value = currentList.toList()
        }
    }

    fun deleteAllSql() {
        shopListDaoRepository.allMetarialDelete()
    }

}
