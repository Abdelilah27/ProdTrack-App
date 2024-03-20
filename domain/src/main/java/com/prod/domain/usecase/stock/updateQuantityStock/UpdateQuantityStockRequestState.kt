package com.prod.domain.usecase.stock.updateQuantityStock

import com.prod.domain.model.Stock


sealed class UpdateQuantityStockRequestState {
    data object Loading : UpdateQuantityStockRequestState()
    data class Success(val stockId: Int) : UpdateQuantityStockRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        UpdateQuantityStockRequestState()
}