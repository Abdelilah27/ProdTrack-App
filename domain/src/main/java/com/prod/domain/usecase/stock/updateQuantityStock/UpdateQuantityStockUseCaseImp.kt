package com.prod.domain.usecase.stock.updateQuantityStock

import com.prod.domain.repository.stock.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UpdateQuantityStockUseCaseImp @Inject constructor(
    private val repository: StockRepository
) : UpdateQuantityStockUseCase {
    // TODO
    override suspend fun invoke(
        stockId: Int,
        newQuantity: Float
    ): Flow<UpdateQuantityStockRequestState> {
        return flow {
            try {
                repository.updateStockQuantity(stockId = stockId, newQuantity = newQuantity)
                emit(UpdateQuantityStockRequestState.Success(stockId))
            } catch (e: Exception) {
                emit(UpdateQuantityStockRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(UpdateQuantityStockRequestState.Loading)
        }
    }
}