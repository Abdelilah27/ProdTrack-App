package com.prod.domain.usecase.production.getProduction

import com.prod.domain.model.Production

sealed class GetProductionRequestState {
    data object Loading : GetProductionRequestState()
    data class Success(val data: Production) : GetProductionRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        GetProductionRequestState()
}