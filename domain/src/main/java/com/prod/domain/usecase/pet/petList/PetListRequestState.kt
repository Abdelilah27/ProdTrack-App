package com.prod.domain.usecase.pet.petList

import com.prod.domain.model.Pet

sealed class PetListRequestState {
    data object Loading : PetListRequestState()
    data class Success(val data: List<Pet>) : PetListRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        PetListRequestState()
}