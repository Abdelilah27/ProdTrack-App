package com.prod.domain.usecase.stock.getStock

import com.prod.domain.model.Stock

sealed class GetStockRequestState {
    data object Loading : GetStockRequestState()
    data class Success(val data: Stock) : GetStockRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        GetStockRequestState()
}