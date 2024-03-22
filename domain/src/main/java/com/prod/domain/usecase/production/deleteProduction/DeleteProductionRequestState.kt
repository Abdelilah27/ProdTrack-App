package com.prod.domain.usecase.production.deleteProduction

import com.prod.domain.model.Production


sealed class DeleteProductionRequestState {
    data object Loading : DeleteProductionRequestState()
    data class Success(val id: Int) : DeleteProductionRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        DeleteProductionRequestState()
}