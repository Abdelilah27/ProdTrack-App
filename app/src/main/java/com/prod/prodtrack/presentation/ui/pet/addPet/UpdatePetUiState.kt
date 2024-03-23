package com.prod.prodtrack.presentation.ui.pet.addPet

sealed class UpdatePetUiState {
    data object Loading : UpdatePetUiState()
    data object Idle : UpdatePetUiState()
    data class Success(val id: Int) : UpdatePetUiState()
    data class Exception(val code: Int, val exception: Throwable) : UpdatePetUiState()
}