package com.prod.prodtrack.presentation.ui.production.addProduction

import com.prod.domain.model.Production

sealed class GetProductionUiState {
    data object Loading : GetProductionUiState()
    data object Idle : GetProductionUiState()
    data class Success(val production: Production) : GetProductionUiState()
    data class Exception(val code: Int, val exception: Throwable) : GetProductionUiState()
}