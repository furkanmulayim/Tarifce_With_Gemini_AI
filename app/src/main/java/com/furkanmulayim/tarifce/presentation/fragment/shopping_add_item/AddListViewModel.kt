package com.furkanmulayim.tarifce.presentation.fragment.shopping_add_item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkanmulayim.tarifce.data.model.ShoppingList

class AddListViewModel : ViewModel() {

    val list: MutableLiveData<List<ShoppingList>> = MutableLiveData()


}