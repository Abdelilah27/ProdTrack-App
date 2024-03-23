package com.prod.domain.usecase.stock.updateStock


sealed class UpdateStockRequestState {
    data object Loading : UpdateStockRequestState()
    data class Success(val id: Int) : UpdateStockRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        UpdateStockRequestState()
}