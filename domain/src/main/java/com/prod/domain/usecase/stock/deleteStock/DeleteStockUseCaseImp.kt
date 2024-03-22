package com.prod.domain.usecase.stock.deleteStock

import com.prod.domain.repository.stock.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class DeleteStockUseCaseImp @Inject constructor(
    private val repository: StockRepository
) : DeleteStockUseCase {
    // TODO
    override suspend fun invoke(id: Int): Flow<DeleteStockRequestState> {
        return flow {
            try {
                repository.deleteStockById(id)
                emit(DeleteStockRequestState.Success(id))
            } catch (e: Exception) {
                emit(DeleteStockRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(DeleteStockRequestState.Loading)
        }
    }
}