package com.prod.domain.usecase.stock.addStock

import com.prod.domain.model.Pet
import com.prod.domain.model.Stock
import kotlinx.coroutines.flow.Flow


interface AddStockUseCase {
    suspend fun invoke(stock: Stock): Flow<AddStockRequestState>
}