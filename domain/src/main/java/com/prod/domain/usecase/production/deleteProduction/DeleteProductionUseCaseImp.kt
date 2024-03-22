package com.prod.domain.usecase.production.deleteProduction

import com.prod.domain.repository.production.ProductionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class DeleteProductionUseCaseImp @Inject constructor(
    private val repository: ProductionRepository
) : DeleteProductionUseCase {
    // TODO
    override suspend fun invoke(id: Int): Flow<DeleteProductionRequestState> {
        return flow {
            try {
                repository.deleteProductionById(id)
                emit(DeleteProductionRequestState.Success(id))
            } catch (e: Exception) {
                emit(DeleteProductionRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(DeleteProductionRequestState.Loading)
        }
    }
}