package com.furkanmulayim.tarifce.di

import android.content.Context
import androidx.room.Room
import com.furkanmulayim.tarifce.data.database.shoplist.ShoppingListDao
import com.furkanmulayim.tarifce.data.database.shoplist.ShoppingListDatabase
import com.furkanmulayim.tarifce.data.repository.ShopListDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleShopListDao {

    @Provides
    @Singleton
    fun provideShopDatabase(@ApplicationContext context: Context): ShoppingListDatabase {
        return Room.databaseBuilder(
            context.applicationContext, ShoppingListDatabase::class.java, "material"
        ).build()
    }

    @Provides
    fun provideShopDao(database: ShoppingListDatabase): ShoppingListDao {
        return database.shopListDao()
    }

    @Provides
    fun provideShopDaoRepository(dao: ShoppingListDao): ShopListDaoRepository {
        return ShopListDaoRepository(dao)
    }
}
