package com.prod.domain.usecase.pet

import com.prod.domain.model.Pet


sealed class AddPetRequestState {
    data object Loading : AddPetRequestState()
    data class Success(val data: Pet) : AddPetRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        AddPetRequestState()
}