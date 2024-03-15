package com.prod.domain.usecase.stock.stockList

import com.prod.domain.model.Stock

sealed class StockListRequestState {
    data object Loading : StockListRequestState()
    data class Success(val data: List<Stock>) : StockListRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        StockListRequestState()
}