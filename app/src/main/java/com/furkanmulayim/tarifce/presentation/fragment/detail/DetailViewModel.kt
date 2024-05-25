package com.furkanmulayim.tarifce.presentation.fragment.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.furkanmulayim.tarifce.base.BaseViewModel
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.repository.FavDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    sharedStateHandle: SavedStateHandle,
    private var favDaoRepository: FavDaoRepository
) :
    BaseViewModel(application) {

    var favItem: MutableLiveData<Food> = MutableLiveData()
    var food: MutableLiveData<Food> = MutableLiveData()

    init {
        sharedStateHandle.get<Food>("FoodDetail").let { data ->
            data?.let {
                food.value = it
                it.id?.let { ids -> isFavProduct(ids) }
            }
        }
    }

    fun saveFavoriProduct(prod: Food) {
        favDaoRepository.saveLikeData(prod)
    }

    fun deleteSingleProducct(id: Int) {
        favDaoRepository.deleteSingleData(id)
    }


    private fun isFavProduct(id: Int) {
        favItem = favDaoRepository.getSingleLikeData(id)
    }
}