package com.furkanmulayim.tarifce.presentation.fragment.choose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkanmulayim.tarifce.data.model.CategoryData
import com.furkanmulayim.tarifce.data.model.Food
import com.furkanmulayim.tarifce.data.model.Material
import com.furkanmulayim.tarifce.data.service.MaterialsAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ChooseViewModel : ViewModel() {


    val categoriesLiveData = MutableLiveData<List<Material>>()
    private val categories = mutableListOf<Material>()

    private val materialAPIService = MaterialsAPIService()
    private val disposable = CompositeDisposable()

    /**
    private val categories = mutableListOf(
    Material(
    "Süt ürünleri", listOf(
    MaterialItem("Süt", R.drawable.finger),
    MaterialItem("Yoğurt", R.drawable.finger),
    MaterialItem("Peynir", R.drawable.finger),
    MaterialItem("Fasulye", R.drawable.finger),
    MaterialItem("Nohut", R.drawable.finger)
    )
    ), Material(
    "Deniz Ürünleri", listOf(
    MaterialItem("Balık", R.drawable.finger),
    MaterialItem("Kalamar", R.drawable.finger),
    MaterialItem("Balık", R.drawable.finger),
    MaterialItem("Balık", R.drawable.finger),
    MaterialItem("Balık", R.drawable.finger),
    MaterialItem("Balık", R.drawable.finger),
    MaterialItem("Balık", R.drawable.finger),
    MaterialItem("Balık", R.drawable.finger)
    )
    ), Material(
    "Deneme", listOf(
    MaterialItem("Dene", R.drawable.finger),
    MaterialItem("Yap", R.drawable.finger),
    MaterialItem("Balık", R.drawable.finger),
    MaterialItem("Kalamar", R.drawable.finger)
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
                .subscribeWith(object : DisposableSingleObserver<List<CategoryData>>() {

                    override fun onSuccess(t: List<CategoryData>) {
                        var i = 0
                        t.forEach { categoryData ->
                            println(categoryData.categories[i].items.forEach {
                                println(it.itemName)
                            })
                            categories.addAll(categoryData.categories)
                            i++
                        }
                        categoriesLiveData.value = categories
                    }

                    override fun onError(e: Throwable) {

                        println("hata."+ e.localizedMessage)
                    }
                })
        )
    }

    fun listMaterial(): List<Material> {
        getFoodFromApi().let {
            return categories
        }
    }
}