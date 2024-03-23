package com.prod.domain.usecase.stock.getStock

import com.prod.domain.repository.stock.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class GetStockUseCaseImp @Inject constructor(private val repository: StockRepository) :
    GetStockUseCase {
    override fun invoke(id: Int): Flow<GetStockRequestState> {
        return flow {
            try {
                repository.getStockById(id).collect { stock ->
                    emit(GetStockRequestState.Success(stock))
                }
            } catch (e: Exception) {
                emit(GetStockRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(GetStockRequestState.Loading)
        }
    }
}

