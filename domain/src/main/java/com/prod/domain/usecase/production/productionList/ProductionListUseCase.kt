package com.prod.domain.usecase.production.productionList

import kotlinx.coroutines.flow.Flow


interface ProductionListUseCase {
    suspend fun invoke(): Flow<ProductionListRequestState>
}