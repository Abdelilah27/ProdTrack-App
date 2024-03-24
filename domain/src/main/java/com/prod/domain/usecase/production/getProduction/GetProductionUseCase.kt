package com.prod.domain.usecase.production.getProduction

import kotlinx.coroutines.flow.Flow


interface GetProductionUseCase {
    fun invoke(
        productionId: Int
    ): Flow<GetProductionRequestState>
}