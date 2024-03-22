package com.prod.domain.usecase.production.deleteProduction

import com.prod.domain.model.Production
import kotlinx.coroutines.flow.Flow


interface DeleteProductionUseCase {
    suspend fun invoke(id: Int): Flow<DeleteProductionRequestState>
}