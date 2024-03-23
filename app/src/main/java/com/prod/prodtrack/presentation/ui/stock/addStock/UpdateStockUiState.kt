package com.prod.prodtrack.presentation.ui.stock.addStock

sealed class UpdateStockUiState {
    data object Loading : UpdateStockUiState()
    data object Idle : UpdateStockUiState()
    data class Success(val id: Int) : UpdateStockUiState()
    data class Exception(val code: Int, val exception: Throwable) : UpdateStockUiState()
}