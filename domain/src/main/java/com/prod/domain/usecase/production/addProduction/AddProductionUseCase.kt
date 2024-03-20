package com.prod.domain.usecase.production.addProduction

import com.prod.domain.model.Pet
import com.prod.domain.model.Production
import kotlinx.coroutines.flow.Flow


interface AddProductionUseCase {
    suspend fun invoke(production: Production): Flow<AddProductionRequestState>
}