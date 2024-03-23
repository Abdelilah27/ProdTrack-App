package com.prod.prodtrack.presentation.ui.pet.addPet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.domain.model.Pet
import com.prod.domain.usecase.pet.addPet.AddPetRequestState
import com.prod.domain.usecase.pet.addPet.AddPetUseCase
import com.prod.domain.usecase.pet.deletePet.DeletePetRequestState
import com.prod.domain.usecase.pet.deletePet.DeletePetUseCase
import com.prod.domain.usecase.pet.getPet.GetPetRequestState
import com.prod.domain.usecase.pet.getPet.GetPetUseCase
import com.prod.domain.usecase.pet.updatePet.UpdatePetRequestState
import com.prod.domain.usecase.pet.updatePet.UpdatePetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddPetViewModel @Inject constructor(
    private val getPetUseCase: GetPetUseCase,
    private val addPetUseCase: AddPetUseCase,
    private val deletePetUseCase: DeletePetUseCase,
    private val updatePetUseCase: UpdatePetUseCase,
) :
    ViewModel() {
    private val _addPetState: MutableStateFlow<AddPetUiState> =
        MutableStateFlow(AddPetUiState.Idle)
    val addPetState: StateFlow<AddPetUiState> = _addPetState.asStateFlow()

    private val _deletePetState = MutableStateFlow<DeletePetUiState>(DeletePetUiState.Idle)
    val deletePetState: StateFlow<DeletePetUiState> = _deletePetState.asStateFlow()

    private val _updatePetState = MutableStateFlow<UpdatePetUiState>(UpdatePetUiState.Idle)
    val updatePetState: StateFlow<UpdatePetUiState> = _updatePetState.asStateFlow()

    private val _getPetState = MutableStateFlow<GetPetUiState>(GetPetUiState.Idle)
    val getPetState: StateFlow<GetPetUiState> = _getPetState.asStateFlow()

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

    fun deletePet(id: Int) {
        viewModelScope.launch {
            deletePetUseCase.invoke(id).collect { deletePetRequestState ->
                _deletePetState.value = when (deletePetRequestState) {
                    is DeletePetRequestState.Exception -> DeletePetUiState.Exception(
                        code = deletePetRequestState.code,
                        exception = deletePetRequestState.exception
                    )

                    is DeletePetRequestState.Success -> DeletePetUiState.Success(
                        id = deletePetRequestState.id
                    )

                    is DeletePetRequestState.Loading -> DeletePetUiState.Loading

                }
            }
        }
    }

    fun updatePet(id: Int, name: String) {
        viewModelScope.launch {
            updatePetUseCase.invoke(id, name).collect { updatePetRequestState ->
                _updatePetState.value = when (updatePetRequestState) {
                    is UpdatePetRequestState.Exception -> UpdatePetUiState.Exception(
                        code = updatePetRequestState.code,
                        exception = updatePetRequestState.exception
                    )

                    is UpdatePetRequestState.Success -> UpdatePetUiState.Success(
                        id
                    )

                    is UpdatePetRequestState.Loading -> UpdatePetUiState.Loading

                }
            }
        }
    }

    fun getPetById(id: Int) {
        viewModelScope.launch {
            getPetUseCase.invoke(id).collect { getPetRequestState ->
                _getPetState.value = when (getPetRequestState) {
                    is GetPetRequestState.Exception -> GetPetUiState.Exception(
                        code = getPetRequestState.code,
                        exception = getPetRequestState.exception
                    )

                    is GetPetRequestState.Success -> GetPetUiState.Success(
                        getPetRequestState.data
                    )

                    is GetPetRequestState.Loading -> GetPetUiState.Loading

                }
            }
        }
    }
}