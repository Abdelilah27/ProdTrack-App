package com.prod.domain.usecase.stock.deleteStock


sealed class DeleteStockRequestState {
    data object Loading : DeleteStockRequestState()
    data class Success(val id: Int) : DeleteStockRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        DeleteStockRequestState()
}