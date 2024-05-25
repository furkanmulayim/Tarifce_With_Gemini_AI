package com.furkanmulayim.tarifce.di

import com.furkanmulayim.tarifce.data.datasource.CategoryDataSource
import com.furkanmulayim.tarifce.data.repository.CategoryRepository
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
class CategoriesAppModule {

    @Provides
    @Singleton
    fun providesCollectionReference(): CollectionReference {
        return Firebase.firestore.collection("Categories")
    } //firebase path'i collection referance türünde döndürür.

    @Provides
    @Singleton
    fun providesCollectCategoryRepo(): CategoryDataSource {
        return CategoryDataSource(providesCollectionReference())
    } //collection reference'i data source'a sağlar

    @Provides
    @Singleton
    fun providesCategoryRepository(categoryDataSource: CategoryDataSource): CategoryRepository {
        return CategoryRepository(categoryDataSource)
    }//datasource nesnesini repositorye inject eder
}