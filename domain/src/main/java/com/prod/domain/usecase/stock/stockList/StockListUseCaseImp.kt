package com.prod.domain.usecase.stock.stockList

import com.prod.domain.repository.stock.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class StockListUseCaseImp @Inject constructor(private val repository: StockRepository) :
    StockListUseCase {
    override suspend fun invoke(): Flow<StockListRequestState> {
        return flow {
            try {
                repository.getAllStocks().collect { stocks ->
                    emit(StockListRequestState.Success(stocks))
                }
            } catch (e: Exception) {
                emit(StockListRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(StockListRequestState.Loading)
        }
    }
}

