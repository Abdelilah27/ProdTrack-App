package com.prod.domain.usecase.production.productionList

import com.prod.domain.repository.production.ProductionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class ProductionListUseCaseImp @Inject constructor(private val repository: ProductionRepository) :
    ProductionListUseCase {
    override suspend fun invoke(): Flow<ProductionListRequestState> {
        return flow {
            try {
                repository.getAllProductions().collect { productions ->
                    emit(ProductionListRequestState.Success(productions))
                }
            } catch (e: Exception) {
                emit(ProductionListRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(ProductionListRequestState.Loading)
        }
    }
}

