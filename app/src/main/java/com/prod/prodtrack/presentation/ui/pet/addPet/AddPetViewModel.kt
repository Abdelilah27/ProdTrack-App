package com.prod.prodtrack.presentation.ui.pet.addPet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.domain.model.Pet
import com.prod.domain.usecase.pet.AddPetRequestState
import com.prod.domain.usecase.pet.AddPetUseCase
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

    private fun addPet(pet: Pet) {
        viewModelScope.launch {
            addPetUseCase.invoke(pet).collect { detailsRequestState ->
                _addPetState.value = when (detailsRequestState) {
                    is AddPetRequestState.Exception -> AddPetUiState.Exception(
                        code = detailsRequestState.code,
                        exception = detailsRequestState.exception
                    )

                    is AddPetRequestState.Success -> AddPetUiState.Success(
                        data = detailsRequestState.data
                    )

                    AddPetRequestState.Loading -> AddPetUiState.Loading
                }
            }
        }
    }

}