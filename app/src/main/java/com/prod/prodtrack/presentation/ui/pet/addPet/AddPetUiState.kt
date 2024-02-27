package com.prod.prodtrack.presentation.ui.pet.addPet

import com.prod.domain.model.Pet

sealed class AddPetUiState {
    data object Loading : AddPetUiState()
    data object Idle : AddPetUiState()
    data class Success(val data: Pet) : AddPetUiState()
    data class Exception(val code: Int, val exception: Throwable) : AddPetUiState()
}