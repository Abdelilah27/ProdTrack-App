package com.prod.prodtrack.presentation.ui.pet.addPet

import com.prod.domain.model.Pet

sealed class GetPetUiState {
    data object Loading : GetPetUiState()
    data object Idle : GetPetUiState()
    data class Success(val pet: Pet) : GetPetUiState()
    data class Exception(val code: Int, val exception: Throwable) : GetPetUiState()
}