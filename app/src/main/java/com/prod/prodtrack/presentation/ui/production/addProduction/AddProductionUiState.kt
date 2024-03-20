package com.prod.prodtrack.presentation.ui.production.addProduction

import com.prod.domain.model.Production

sealed class AddProductionUiState {
    data object Loading : AddProductionUiState()
    data object Idle : AddProductionUiState()
    data class Success(val data: Production) : AddProductionUiState()
    data class Exception(val code: Int, val exception: Throwable) : AddProductionUiState()
}