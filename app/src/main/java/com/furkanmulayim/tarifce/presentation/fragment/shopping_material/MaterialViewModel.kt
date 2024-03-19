package com.furkanmulayim.tarifce.presentation.fragment.shopping_material

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.tarifce.data.model.Category
import com.furkanmulayim.tarifce.data.model.CategoryData
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.data.model.Shopliste
import com.furkanmulayim.tarifce.repository.ShopListDaoRepository
import com.furkanmulayim.tarifce.data.service.materials.MaterialsAPIService
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MaterialViewModel @Inject constructor(application: Application, private var sldao: ShopListDaoRepository
) : BaseViewModel(application) {

    fun getData(){
        getFoodFromApi()
    }

    // download data from API
    val categoriesLiveData = MutableLiveData<List<Category>>()
    private val materialAPIService = MaterialsAPIService()
    private val disposable = CompositeDisposable()
    private fun getFoodFromApi() {
        disposable.add(
            materialAPIService.getMaterials()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CategoryData>>() {

                    override fun onSuccess(categoryDataList: List<CategoryData>) {
                        val allCategories = mutableListOf<Category>()
                        categoryDataList.forEach { categoryData ->
                            categoryData.categories.forEach { category ->
                                val materials = category.materials.map {
                                    Material(it.name, it.imageUrl)
                                }
                                allCategories.add(
                                    Category(category.name,
                                        materials
                                    )
                                )
                            }
                        }
                        categoriesLiveData.value = allCategories
                    }
                    override fun onError(e: Throwable) {
                    }
                })
        )
    }


    //save data sqlite
    val list: MutableLiveData<List<Shopliste>> = MutableLiveData()
    fun saveDatabase(list: List<Shopliste>): Boolean {
        var isComplete = false
        viewModelScope.launch {
            if (list.isNotEmpty()) {
                for (item in list) {
                    sldao.saveFoods(
                        id = 0,
                        name = item.name,
                        image = item.image,
                        weight = item.weight,
                        explain = item.explain,
                        issold = item.issold
                    )
                }
                isComplete = true
            }
        }
        return isComplete
    }
}