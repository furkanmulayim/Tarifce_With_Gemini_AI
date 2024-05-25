package com.furkanmulayim.tarifce.presentation.fragment.categorie

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.tarifce.base.BaseViewModel
import com.furkanmulayim.tarifce.data.model.CategoryData
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.data.repository.CategoryRepository
import com.furkanmulayim.tarifce.data.repository.ShopListDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
@Inject
constructor(
    application: Application,
    private var categoryRepository: CategoryRepository,
    private var shopListDaoRepository: ShopListDaoRepository,
    private var sharedStateHandle: SavedStateHandle
) : BaseViewModel(application) {

    var category: MutableLiveData<ArrayList<CategoryData>> = MutableLiveData()
    var fragmentState: MutableLiveData<String> = MutableLiveData()

    init {
        getBundle()
        getCategory()
    }

    private fun getBundle() {
        sharedStateHandle.get<String>("fragmentState").let {
            fragmentState.value = it
        }
    }

    private fun getCategory() {
        category = categoryRepository.getData()
    }

    fun setBundleList(list: ArrayList<Material>): Bundle {
        var stringListBundle = ""
        list.forEach {
            stringListBundle = it.name.plus(" ".plus(stringListBundle))
        }

        val bundle = Bundle().apply {
            putString("selectedList", stringListBundle)
        }
        return bundle
    }

    fun saveDatabase(list: List<Material>): Boolean {
        var isComplete = false
        viewModelScope.launch {
            if (list.isNotEmpty()) {
                for (item in list) {
                    shopListDaoRepository.saveFoods(item).let {
                        isComplete = true
                    }
                }
            }
        }
        return isComplete
    }

}