package com.prod.domain.usecase.stock.updateQuantityStock

import com.prod.domain.model.Pet
import com.prod.domain.model.Stock
import kotlinx.coroutines.flow.Flow


interface UpdateQuantityStockUseCase {
    suspend fun invoke(stockId: Int, newQuantity: Float): Flow<UpdateQuantityStockRequestState>
}