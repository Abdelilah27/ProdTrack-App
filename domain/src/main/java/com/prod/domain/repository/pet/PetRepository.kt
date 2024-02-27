package com.prod.domain.repository.pet

import com.prod.domain.model.Pet

interface PetRepository {
    suspend fun addPet(pet: Pet)
}