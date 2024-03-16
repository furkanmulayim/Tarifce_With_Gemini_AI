package com.furkanmulayim.tarifce.presentation.fragment.shopping

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.tarifce.data.model.Shopliste
import com.furkanmulayim.tarifce.data.repo.ShopListDaoRepository
import com.furkanmulayim.tarifce.presentation.BaseViewModel
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
    }

    fun updateItem(id: Int, issold: Int) {
        sldao.updateMaterial(id, issold)
    }

}
