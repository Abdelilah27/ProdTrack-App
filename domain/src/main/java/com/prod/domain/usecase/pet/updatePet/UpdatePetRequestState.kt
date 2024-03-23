package com.prod.domain.usecase.pet.updatePet


sealed class UpdatePetRequestState {
    data object Loading : UpdatePetRequestState()
    data class Success(val id: Int) : UpdatePetRequestState()
    data class Exception(val code: Int = -1, val exception: Throwable) :
        UpdatePetRequestState()
}