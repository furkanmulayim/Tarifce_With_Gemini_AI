package com.furkanmulayim.tarifce.di

import android.content.Context
import androidx.room.Room
import com.furkanmulayim.tarifce.data.database.foods.FavDao
import com.furkanmulayim.tarifce.data.database.foods.FavDatabase
import com.furkanmulayim.tarifce.data.repository.FavDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleFavDao {

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): FavDatabase {
        return Room.databaseBuilder(
            context.applicationContext, FavDatabase::class.java, "foods"
        ).build()
    }

    @Provides
    fun provideProductDao(database: FavDatabase): FavDao {
        return database.productDao()
    }

    @Provides
    fun provideProductDaoRepository(dao: FavDao): FavDaoRepository {
        return FavDaoRepository(dao)
    }
}
