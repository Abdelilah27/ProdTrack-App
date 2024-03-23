package com.prod.prodtrack.presentation.ui.stock.addStock

import com.prod.domain.model.Stock

sealed class GetStockUiState {
    data object Loading : GetStockUiState()
    data object Idle : GetStockUiState()
    data class Success(val stock: Stock) : GetStockUiState()
    data class Exception(val code: Int, val exception: Throwable) : GetStockUiState()
}