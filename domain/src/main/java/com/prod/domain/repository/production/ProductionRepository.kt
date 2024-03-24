package com.prod.domain.repository.production

import com.prod.domain.model.Production
import com.prod.domain.model.Stock
import kotlinx.coroutines.flow.Flow

interface ProductionRepository {
    suspend fun addProduction(production: Production)
    fun getAllProductions(): Flow<List<Production>>

    suspend fun deleteProductionById(id: Int)

    suspend fun updateProduction(
        productionId: Int,
        petId: Int,
        stockId: Int,
        date: String,
        producing: Float,
        quantity: Float
    )

    fun getProductionById(id: Int): Flow<Production>


}