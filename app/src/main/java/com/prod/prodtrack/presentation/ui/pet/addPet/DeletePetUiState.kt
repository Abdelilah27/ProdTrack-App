package com.prod.prodtrack.presentation.ui.pet.addPet

sealed class DeletePetUiState {
    data object Loading : DeletePetUiState()
    data object Idle : DeletePetUiState()
    data class Success(val id: Int) : DeletePetUiState()
    data class Exception(val code: Int, val exception: Throwable) : DeletePetUiState()
}