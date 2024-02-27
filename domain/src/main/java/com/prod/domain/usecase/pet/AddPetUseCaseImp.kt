package com.prod.domain.usecase.pet

import com.prod.domain.model.Pet
import com.prod.domain.repository.pet.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class AddPetUseCaseImp @Inject constructor(
    private val repository: PetRepository
) : AddPetUseCase {
    // TODO
    override suspend fun invoke(pet: Pet): Flow<AddPetRequestState> {
        return flow {
            try {
                repository.addPet(pet)
                emit(AddPetRequestState.Success(pet))
            } catch (e: Exception) {
                emit(AddPetRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(AddPetRequestState.Loading)
        }
    }
}