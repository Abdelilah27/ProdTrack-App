package com.prod.domain.usecase.pet.deletePet

import com.prod.domain.model.Pet
import kotlinx.coroutines.flow.Flow


interface DeletePetUseCase {
    suspend fun invoke(id: Int): Flow<DeletePetRequestState>
}