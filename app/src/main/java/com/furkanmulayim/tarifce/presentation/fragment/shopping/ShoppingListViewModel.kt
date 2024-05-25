package com.furkanmulayim.tarifce.presentation.fragment.shopping

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.base.BaseViewModel
import com.furkanmulayim.tarifce.data.model.Shopliste
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {

    var shopList = MutableLiveData<List<Shopliste>>()

    fun getList() {
    }

    fun deleteItem(id: Int) {
        //sldao.deleteMaterial(id)

        //deleted item for adapterList
        val currentList = shopList.value.orEmpty().toMutableList()
        val deletedItem = currentList.find { it.id == id }
        deletedItem?.let {
            currentList.remove(it)
            shopList.value = currentList.toList()
        }
    }

    fun deleteAllSql() {
        //sldao.allMetarialDelete()
    }

    fun updateItem(id: Int, issold: Int) {
        //sldao.updateMaterial(id, issold)
    }
}
