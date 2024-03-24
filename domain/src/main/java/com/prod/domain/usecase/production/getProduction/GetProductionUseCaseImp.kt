package com.prod.domain.usecase.production.getProduction

import com.prod.domain.repository.production.ProductionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class GetProductionUseCaseImp @Inject constructor(private val repository: ProductionRepository) :
    GetProductionUseCase {
    override fun invoke(id: Int): Flow<GetProductionRequestState> {
        return flow {
            try {
                repository.getProductionById(id).collect { stock ->
                    emit(GetProductionRequestState.Success(stock))
                }
            } catch (e: Exception) {
                emit(GetProductionRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(GetProductionRequestState.Loading)
        }
    }
}

