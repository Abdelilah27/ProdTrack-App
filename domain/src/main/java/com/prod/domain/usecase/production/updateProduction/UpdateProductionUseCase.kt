package com.prod.domain.usecase.production.updateProduction

import kotlinx.coroutines.flow.Flow


interface UpdateProductionUseCase {
    suspend fun invoke(
        productionId: Int,
        petId: Int,
        stockId: Int,
        date: String,
        producing: Float,
        quantity: Float
    ): Flow<UpdateProductionRequestState>
}