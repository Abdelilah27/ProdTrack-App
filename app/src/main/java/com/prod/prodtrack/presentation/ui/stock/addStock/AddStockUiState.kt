package com.prod.prodtrack.presentation.ui.stock.addStock

import com.prod.domain.model.Stock

sealed class AddStockUiState {
    data object Loading : AddStockUiState()
    data object Idle : AddStockUiState()
    data class Success(val data: Stock) : AddStockUiState()
    data class Exception(val code: Int, val exception: Throwable) : AddStockUiState()
}