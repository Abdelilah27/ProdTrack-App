package com.prod.data.di

import com.prod.data.repository.pet.PetRepositoryImp
import com.prod.data.repository.production.ProductionRepositoryImp
import com.prod.data.repository.stock.StockRepositoryImp
import com.prod.domain.repository.pet.PetRepository
import com.prod.domain.repository.production.ProductionRepository
import com.prod.domain.repository.stock.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsPetRepository(petRepositoryImp: PetRepositoryImp)
            : PetRepository

    @Binds
    abstract fun bindsStockRepository(stockRepositoryImp: StockRepositoryImp)
            : StockRepository

    @Binds
    abstract fun bindsProductionRepository(productionRepositoryImp: ProductionRepositoryImp)
            : ProductionRepository
}