package com.furkanmulayim.tarifce.presentation.fragment.choose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkanmulayim.tarifce.data.model.CategoryData
import com.furkanmulayim.tarifce.data.model.Category
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.data.model.MaterialItem
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

    /**
    private val categories = mutableListOf(
    Category(
    "Süt ürünleri", listOf(
    Material("Süt", R.drawable.finger),
    Material("Yoğurt", R.drawable.finger),
    Material("Peynir", R.drawable.finger),
    Material("Fasulye", R.drawable.finger),
    Material("Nohut", R.drawable.finger)
    )
    ), Category(
    "Deniz Ürünleri", listOf(
    Material("Balık", R.drawable.finger),
    Material("Kalamar", R.drawable.finger),
    Material("Balık", R.drawable.finger),
    Material("Balık", R.drawable.finger),
    Material("Balık", R.drawable.finger),
    Material("Balık", R.drawable.finger),
    Material("Balık", R.drawable.finger),
    Material("Balık", R.drawable.finger)
    )
    ), Category(
    "Deneme", listOf(
    Material("Dene", R.drawable.finger),
    Material("Yap", R.drawable.finger),
    Material("Balık", R.drawable.finger),
    Material("Kalamar", R.drawable.finger)
    )
    )
    ) */

    // download data from API
    fun getFoodFromApi() {
        println("1.")
        disposable.add(
            materialAPIService.getMaterials()
                //async new thread
                .subscribeOn(Schedulers.newThread())
                //show main Thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CategoryData>() {

                    override fun onSuccess(categoryData: CategoryData) {
                        val allCategories = mutableListOf<Category>()
                        categoryData.categories.forEach { category ->
                            category.materials.forEach { material ->
                                allCategories.add(Category(category.name, listOf(Material(material.name, material.imageUrl))))
                            }
                        }
                        categoriesLiveData.value = allCategories
                    }

                    override fun onError(e: Throwable) {
                        println("hata: ${e.localizedMessage}")
                    }
                })
        )
    }


    fun listMaterial(): List<Category> {
        getFoodFromApi().let {
            return categories
        }
    }
}