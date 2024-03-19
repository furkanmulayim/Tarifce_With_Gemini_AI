package com.furkanmulayim.tarifce.presentation.fragment.shopping

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.model.Shopliste
import com.furkanmulayim.tarifce.repository.ShopListDaoRepository
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    application: Application, private var sldao: ShopListDaoRepository
) : BaseViewModel(application) {

    var cardList: MutableLiveData<List<Shopliste>>

    var isSelectedAdapter: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        getCardList()
        cardList = sldao.shopListPostViewModel()
    }

    private fun getCardList() {
        sldao.getAllFoods()
    }

    fun deleteItem(id: Int) {
        sldao.deleteMaterial(id)

        //deleted item for adapterList
        val currentList = cardList.value.orEmpty().toMutableList()
        val deletedItem  = currentList.find { it.id == id }
        deletedItem?.let {
            currentList.remove(it)
            cardList.value = currentList.toList()
        }
    }

    fun updateItem(id: Int, issold: Int) {
        sldao.updateMaterial(id, issold)
    }

}
