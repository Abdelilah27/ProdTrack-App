package com.prod.prodtrack.presentation.ui.stock.stockList

import com.prod.domain.model.Stock

sealed class StockListUiState {
    data object Loading : StockListUiState()
    data object Idle : StockListUiState()
    data class Success(val data: List<Stock>) : StockListUiState()
    data class Exception(val code: Int, val exception: Throwable) : StockListUiState()
}