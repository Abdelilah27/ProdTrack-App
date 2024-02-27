package com.prod.domain.usecase.pet

import com.prod.domain.model.Pet
import kotlinx.coroutines.flow.Flow


interface AddPetUseCase {
    suspend fun invoke(pet: Pet): Flow<AddPetRequestState>
}