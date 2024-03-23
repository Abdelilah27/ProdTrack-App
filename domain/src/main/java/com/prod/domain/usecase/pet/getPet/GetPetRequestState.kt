package com.prod.domain.usecase.pet.getPet

import com.prod.domain.model.Pet

sealed class GetPetRequestState {
    data object Loading : GetPetRequestState()
    data class Success(val data: Pet) : GetPetRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        GetPetRequestState()
}