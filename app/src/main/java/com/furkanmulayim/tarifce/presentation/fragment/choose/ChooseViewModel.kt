package com.furkanmulayim.tarifce.presentation.fragment.choose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkanmulayim.tarifce.data.model.CategoryData
import com.furkanmulayim.tarifce.data.model.Category
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.data.service.MaterialsAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ChooseViewModel : ViewModel() {


    val categoriesLiveData = MutableLiveData<List<Category>>()
    private val categories = mutableListOf<Category>()

    private val materialAPIService = MaterialsAPIService()
    private val disposable = CompositeDisposable()

    fun getData(){
        getFoodFromApi()
    }


    // download data from API
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
                                    // Veriler null değilse Material objesini oluştur
                                    Material(it.name, it.imageUrl)
                                }
                                // Kategori ve materyalleri ekle
                                allCategories.add(Category(category.name, materials))
                            }
                        }
                        // LiveData'ya verileri aktar
                        categoriesLiveData.value = allCategories
                    }

                    override fun onError(e: Throwable) {
                        println("hata: ${e.localizedMessage}")
                    }
                })
        )
    }


}