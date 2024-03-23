package com.prod.domain.repository.pet

import com.prod.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    suspend fun addPet(pet: Pet)
    fun getAllPets(): Flow<List<Pet>>
    suspend fun deletePetById(id: Int)

    suspend fun updatePet(id: Int, name: String)

    fun getPetById(id: Int): Flow<Pet>

}