package com.prod.domain.usecase.pet.petList

import kotlinx.coroutines.flow.Flow


interface PetListUseCase {
    fun invoke(): Flow<PetListRequestState>
}