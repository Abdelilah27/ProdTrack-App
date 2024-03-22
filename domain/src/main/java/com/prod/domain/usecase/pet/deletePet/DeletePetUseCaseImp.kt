package com.prod.domain.usecase.pet.deletePet

import com.prod.domain.repository.pet.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class DeletePetUseCaseImp @Inject constructor(
    private val repository: PetRepository
) : DeletePetUseCase {
    // TODO
    override suspend fun invoke(id: Int): Flow<DeletePetRequestState> {
        return flow {
            try {
                repository.deletePetById(id)
                emit(DeletePetRequestState.Success(id))
            } catch (e: Exception) {
                emit(DeletePetRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(DeletePetRequestState.Loading)
        }
    }
}