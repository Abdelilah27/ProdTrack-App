package com.prod.domain.usecase.stock.updateStock

import kotlinx.coroutines.flow.Flow


interface UpdateStockUseCase {
    suspend fun invoke(id: Int, name: String, quantity: Float): Flow<UpdateStockRequestState>
}