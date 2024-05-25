package com.furkanmulayim.tarifce.di

import com.furkanmulayim.tarifce.data.datasource.FoodDataSource
import com.furkanmulayim.tarifce.data.repository.FoodRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FoodsAppModule {

    @Provides
    @Singleton
    fun providesCollectionReference(): CollectionReference {
        return Firebase.firestore.collection("Food")
    } //firebase path'i collection referance türünde döndürür.

    @Provides
    @Singleton
    fun providesCollectFoodRepo(): FoodDataSource {
        return FoodDataSource(providesCollectionReference())
    } //collection reference'i data source'a sağlar

    @Provides
    @Singleton
    fun providesFoodsRepository(foodsDataSource: FoodDataSource): FoodRepository {
        return FoodRepository(foodsDataSource)
    }//datasource nesnesini repositorye inject eder

}