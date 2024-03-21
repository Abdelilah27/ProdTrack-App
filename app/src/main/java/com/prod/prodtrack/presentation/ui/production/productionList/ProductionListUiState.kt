package com.prod.prodtrack.presentation.ui.production.productionList

import com.prod.domain.model.Production

sealed class ProductionListUiState {
    data object Loading : ProductionListUiState()
    data object Idle : ProductionListUiState()
    data class Success(val data: List<Production>) : ProductionListUiState()
    data class Exception(val code: Int, val exception: Throwable) : ProductionListUiState()
}