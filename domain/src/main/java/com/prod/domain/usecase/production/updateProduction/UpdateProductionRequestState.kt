package com.prod.domain.usecase.production.updateProduction


sealed class UpdateProductionRequestState {
    data object Loading : UpdateProductionRequestState()
    data class Success(val id: Int) : UpdateProductionRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        UpdateProductionRequestState()
}