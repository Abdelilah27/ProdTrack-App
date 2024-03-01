package com.prod.domain.usecase.pet.petList

import kotlinx.coroutines.flow.Flow


interface PetListUseCase {
    suspend fun invoke(): Flow<PetListRequestState>
}