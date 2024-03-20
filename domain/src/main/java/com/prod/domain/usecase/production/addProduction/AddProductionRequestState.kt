package com.prod.domain.usecase.production.addProduction

import com.prod.domain.model.Production


sealed class AddProductionRequestState {
    data object Loading : AddProductionRequestState()
    data class Success(val data: Production) : AddProductionRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        AddProductionRequestState()
}