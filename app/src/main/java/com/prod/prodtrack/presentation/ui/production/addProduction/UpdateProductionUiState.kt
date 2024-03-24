package com.prod.prodtrack.presentation.ui.production.addProduction

sealed class UpdateProductionUiState {
    data object Loading : UpdateProductionUiState()
    data object Idle : UpdateProductionUiState()
    data class Success(val id: Int) : UpdateProductionUiState()
    data class Exception(val code: Int, val exception: Throwable) : UpdateProductionUiState()
}