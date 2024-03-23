package com.prod.domain.usecase.pet.updatePet

import com.prod.domain.model.Pet
import kotlinx.coroutines.flow.Flow


interface UpdatePetUseCase {
    suspend fun invoke(id: Int, name: String): Flow<UpdatePetRequestState>
}