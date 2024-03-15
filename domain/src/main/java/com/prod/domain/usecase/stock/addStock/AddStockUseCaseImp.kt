package com.prod.domain.usecase.stock.addStock

import com.prod.domain.model.Stock
import com.prod.domain.repository.stock.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class AddStockUseCaseImp @Inject constructor(
    private val repository: StockRepository
) : AddStockUseCase {
    // TODO
    override suspend fun invoke(stock: Stock): Flow<AddStockRequestState> {
        return flow {
            try {
                repository.addStock(stock)
                emit(AddStockRequestState.Success(stock))
            } catch (e: Exception) {
                emit(AddStockRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(AddStockRequestState.Loading)
        }
    }
}