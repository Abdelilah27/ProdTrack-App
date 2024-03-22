package com.prod.domain.repository.production

import com.prod.domain.model.Production
import kotlinx.coroutines.flow.Flow

interface ProductionRepository {
    suspend fun addProduction(production: Production)
    fun getAllProductions(): Flow<List<Production>>

    suspend fun deleteProductionById(id: Int)

}