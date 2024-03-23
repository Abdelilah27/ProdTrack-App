package com.prod.prodtrack.presentation.ui.pet.petList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.domain.usecase.pet.deletePet.DeletePetRequestState
import com.prod.domain.usecase.pet.deletePet.DeletePetUseCase
import com.prod.domain.usecase.pet.petList.PetListRequestState
import com.prod.domain.usecase.pet.petList.PetListUseCase
import com.prod.prodtrack.presentation.ui.pet.addPet.DeletePetUiState
import com.prod.prodtrack.presentation.ui.utils.intent.PetListIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetListViewModel @Inject constructor(
    private val petListUseCase: PetListUseCase,
) : ViewModel() {

    private val _petListState = MutableStateFlow<PetListRequestState>(PetListRequestState.Loading)
    val petListState: StateFlow<PetListRequestState> = _petListState

    init {
        processIntent(PetListIntent.PetList)
    }

    fun processIntent(intent: PetListIntent) {
        if (intent is PetListIntent.PetList) {
            viewModelScope.launch {
                fetchPets()
            }
        }
    }

    private suspend fun fetchPets() {
        viewModelScope.launch {
            try {
                petListUseCase.invoke().collect { petListRequestState ->
                    _petListState.value = petListRequestState
                }
            } catch (e: Exception) {
                _petListState.value = PetListRequestState.Exception(exception = e)
            }
        }
    }


}