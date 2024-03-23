package com.prod.domain.usecase.pet.getPet

import kotlinx.coroutines.flow.Flow


interface GetPetUseCase {
    fun invoke(id: Int): Flow<GetPetRequestState>
}