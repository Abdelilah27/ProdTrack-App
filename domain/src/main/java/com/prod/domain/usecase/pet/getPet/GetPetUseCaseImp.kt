package com.prod.domain.usecase.pet.getPet

import com.prod.domain.repository.pet.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class GetPetUseCaseImp @Inject constructor(private val repository: PetRepository) :
    GetPetUseCase {
    override fun invoke(id: Int): Flow<GetPetRequestState> {
        return flow {
            try {
                repository.getPetById(id).collect { pet ->
                    emit(GetPetRequestState.Success(pet))
                }
            } catch (e: Exception) {
                emit(GetPetRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(GetPetRequestState.Loading)
        }
    }
}

