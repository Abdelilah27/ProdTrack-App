package com.prod.domain.usecase.stock.stockList

import kotlinx.coroutines.flow.Flow


interface StockListUseCase {
    suspend fun invoke(): Flow<StockListRequestState>
}