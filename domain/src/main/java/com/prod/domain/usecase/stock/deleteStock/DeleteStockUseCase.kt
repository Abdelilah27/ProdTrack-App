package com.prod.domain.usecase.stock.deleteStock

import com.prod.domain.model.Pet
import com.prod.domain.model.Stock
import kotlinx.coroutines.flow.Flow


interface DeleteStockUseCase {
    suspend fun invoke(id: Int): Flow<DeleteStockRequestState>
}