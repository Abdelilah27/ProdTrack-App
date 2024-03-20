package com.prod.prodtrack.presentation.ui.production.addProduction

import com.prod.domain.model.Production

sealed class UpdateQuantityStockUiState {
    data object Loading : UpdateQuantityStockUiState()
    data object Idle : UpdateQuantityStockUiState()
    data class Success(val stockID: Int) : UpdateQuantityStockUiState()
    data class Exception(val code: Int, val exception: Throwable) : UpdateQuantityStockUiState()
}