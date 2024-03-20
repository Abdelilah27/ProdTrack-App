package com.prod.domain.di

import com.prod.domain.usecase.pet.addPet.AddPetUseCase
import com.prod.domain.usecase.pet.addPet.AddPetUseCaseImp
import com.prod.domain.usecase.pet.petList.PetListUseCase
import com.prod.domain.usecase.pet.petList.PetListUseCaseImp
import com.prod.domain.usecase.production.addProduction.AddProductionUseCase
import com.prod.domain.usecase.production.addProduction.AddProductionUseCaseImp
import com.prod.domain.usecase.stock.addStock.AddStockUseCase
import com.prod.domain.usecase.stock.addStock.AddStockUseCaseImp
import com.prod.domain.usecase.stock.stockList.StockListUseCase
import com.prod.domain.usecase.stock.stockList.StockListUseCaseImp
import com.prod.domain.usecase.stock.updateQuantityStock.UpdateQuantityStockUseCase
import com.prod.domain.usecase.stock.updateQuantityStock.UpdateQuantityStockUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindsAddPetUseCase(addPetUseCaseImp: AddPetUseCaseImp): AddPetUseCase

    @Binds
    abstract fun bindsPetListUseCase(petListUseCaseImp: PetListUseCaseImp): PetListUseCase

    @Binds
    abstract fun bindsAddStockUseCase(addStockUseCaseImp: AddStockUseCaseImp): AddStockUseCase

    @Binds
    abstract fun bindsStockListUseCase(stockListUseCaseImp: StockListUseCaseImp): StockListUseCase

    @Binds
    abstract fun bindsAddProductionUseCase(addProductionUseCaseImp: AddProductionUseCaseImp): AddProductionUseCase

    @Binds
    abstract fun bindsUpdateQuantityStockUseCase(updateQuantityStockUseCaseImp: UpdateQuantityStockUseCaseImp): UpdateQuantityStockUseCase
}

