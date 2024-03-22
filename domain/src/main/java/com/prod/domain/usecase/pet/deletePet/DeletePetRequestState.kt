package com.prod.domain.usecase.pet.deletePet

import com.prod.domain.model.Pet


sealed class DeletePetRequestState {
    data object Loading : DeletePetRequestState()
    data class Success(val id: Int) : DeletePetRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        DeletePetRequestState()
}