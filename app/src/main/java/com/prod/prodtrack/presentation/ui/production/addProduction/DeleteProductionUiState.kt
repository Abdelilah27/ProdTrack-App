package com.prod.prodtrack.presentation.ui.production.addProduction

sealed class DeleteProductionUiState {
    data object Loading : DeleteProductionUiState()
    data object Idle : DeleteProductionUiState()
    data class Success(val id: Int) : DeleteProductionUiState()
    data class Exception(val code: Int, val exception: Throwable) : DeleteProductionUiState()
}