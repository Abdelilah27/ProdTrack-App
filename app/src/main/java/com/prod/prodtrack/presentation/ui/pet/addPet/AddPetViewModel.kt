package com.prod.prodtrack.presentation.ui.pet.addPet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.domain.model.Pet
import com.prod.domain.usecase.pet.addPet.AddPetRequestState
import com.prod.domain.usecase.pet.addPet.AddPetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddPetViewModel @Inject constructor(
    private val addPetUseCase: AddPetUseCase,
) :
    ViewModel() {
    private val _addPetState: MutableStateFlow<AddPetUiState> =
        MutableStateFlow(AddPetUiState.Idle)
    val addPetState: StateFlow<AddPetUiState> = _addPetState.asStateFlow()

    fun addPet(pet: Pet) {
        viewModelScope.launch {
            addPetUseCase.invoke(pet).collect { addPetRequestState ->
                _addPetState.value = when (addPetRequestState) {
                    is AddPetRequestState.Exception -> AddPetUiState.Exception(
                        code = addPetRequestState.code,
                        exception = addPetRequestState.exception
                    )

                    is AddPetRequestState.Success -> AddPetUiState.Success(
                        data = addPetRequestState.data
                    )

                    is AddPetRequestState.Loading -> AddPetUiState.Loading

                }
            }
        }
    }
}