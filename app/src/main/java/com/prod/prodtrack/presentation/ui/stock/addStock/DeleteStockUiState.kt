package com.prod.prodtrack.presentation.ui.stock.addStock

sealed class DeleteStockUiState {
    data object Loading : DeleteStockUiState()
    data object Idle : DeleteStockUiState()
    data class Success(val id: Int) : DeleteStockUiState()
    data class Exception(val code: Int, val exception: Throwable) : DeleteStockUiState()
}