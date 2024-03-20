package com.prod.domain.usecase.production.addProduction

import com.prod.domain.model.Production
import com.prod.domain.repository.production.ProductionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class AddProductionUseCaseImp @Inject constructor(
    private val repository: ProductionRepository
) : AddProductionUseCase {
    // TODO
    override suspend fun invoke(production: Production): Flow<AddProductionRequestState> {
        return flow {
            try {
                repository.addProduction(production)
                emit(AddProductionRequestState.Success(production))
            } catch (e: Exception) {
                emit(AddProductionRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(AddProductionRequestState.Loading)
        }
    }
}