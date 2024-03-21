package com.prod.domain.usecase.production.productionList

import com.prod.domain.model.Production

sealed class ProductionListRequestState {
    data object Loading : ProductionListRequestState()
    data class Success(val data: List<Production>) : ProductionListRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        ProductionListRequestState()
}