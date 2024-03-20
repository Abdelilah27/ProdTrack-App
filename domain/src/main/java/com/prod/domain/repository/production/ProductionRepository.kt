package com.prod.domain.repository.production

import com.prod.domain.model.Pet
import com.prod.domain.model.Production
import kotlinx.coroutines.flow.Flow

interface ProductionRepository {
    suspend fun addProduction(production: Production)
    // fun getAllPets(): Flow<List<Pet>>
}