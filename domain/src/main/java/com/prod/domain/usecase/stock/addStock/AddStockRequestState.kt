package com.prod.domain.usecase.stock.addStock

import com.prod.domain.model.Stock


sealed class AddStockRequestState {
    data object Loading : AddStockRequestState()
    data class Success(val data: Stock) : AddStockRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        AddStockRequestState()
}