package com.prod.domain.usecase.pet.updatePet

import com.prod.domain.repository.pet.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UpdatePetUseCaseImp @Inject constructor(
    private val repository: PetRepository
) : UpdatePetUseCase {
    // TODO
    override suspend fun invoke(id: Int, name: String): Flow<UpdatePetRequestState> {
        return flow {
            try {
                repository.updatePet(id, name)
                emit(UpdatePetRequestState.Success(id))
            } catch (e: Exception) {
                emit(UpdatePetRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(UpdatePetRequestState.Loading)
        }
    }
}