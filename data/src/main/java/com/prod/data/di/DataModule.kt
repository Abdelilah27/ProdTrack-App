package com.prod.data.di

import android.content.Context
import androidx.room.Room
import com.prod.data.dataSource.core.Constants.Companion.PRODTRACK_DATABASE
import com.prod.data.dataSource.db.ProdTrackDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideProdTrackDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        ProdTrackDb::class.java,
        PRODTRACK_DATABASE
    ).build()

    @Provides
    fun providePetDao(
        prodTrackDb: ProdTrackDb
    ) = prodTrackDb.petDao

    @Provides
    fun provideStockDao(
        prodTrackDb: ProdTrackDb
    ) = prodTrackDb.stockDao

    @Provides
    fun provideProductionDao(
        prodTrackDb: ProdTrackDb
    ) = prodTrackDb.productionDao
}