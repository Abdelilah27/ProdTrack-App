package com.prod.domain.usecase.pet.petList

import com.prod.domain.repository.pet.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class PetListUseCaseImp @Inject constructor(private val repository: PetRepository) :
    PetListUseCase {
    override fun invoke(): Flow<PetListRequestState> {
        return flow {
            try {
                repository.getAllPets().collect { pets ->
                    emit(PetListRequestState.Success(pets))
                }
            } catch (e: Exception) {
                emit(PetListRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(PetListRequestState.Loading)
        }
    }
}

