package com.prod.prodtrack.presentation.ui.pet.petList

import com.prod.domain.model.Pet

sealed class PetListUiState {
    data object Loading : PetListUiState()
    data object Idle : PetListUiState()
    data class Success(val data: List<Pet>) : PetListUiState()
    data class Exception(val code: Int, val exception: Throwable) : PetListUiState()
}