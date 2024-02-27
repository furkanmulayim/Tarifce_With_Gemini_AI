package com.furkanmulayim.tarifce.di

import android.content.Context
import androidx.room.Room
import com.furkanmulayim.tarifce.data.db.FoodDao
import com.furkanmulayim.tarifce.data.db.FoodDatabase
import com.furkanmulayim.tarifce.data.repo.FoodDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCardsDaoRepository(fdao: FoodDao): FoodDaoRepository {
        return FoodDaoRepository(fdao)
    }

    @Provides
    @Singleton
    fun provideCardsDao(@ApplicationContext context: Context): FoodDao {
        val vt = Room.databaseBuilder(context, FoodDatabase::class.java, name = "food.sqlite")
            .createFromAsset("food.sqlite").build()
        return vt.foodDao()
    }
}