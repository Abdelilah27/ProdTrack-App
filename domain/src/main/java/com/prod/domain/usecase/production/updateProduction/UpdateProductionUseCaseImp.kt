package com.prod.domain.usecase.production.updateProduction

import com.prod.domain.repository.production.ProductionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UpdateProductionUseCaseImp @Inject constructor(
    private val repository: ProductionRepository
) : UpdateProductionUseCase {
    // TODO
    override suspend fun invoke(
        productionId: Int,
        petId: Int,
        stockId: Int,
        date: String,
        producing: Float,
        quantity: Float
    ): Flow<UpdateProductionRequestState> {
        return flow {
            try {
                repository.updateProduction(
                    productionId = productionId,
                    petId = petId,
                    stockId = stockId,
                    date = date,
                    producing = producing,
                    quantity = quantity
                )
                emit(UpdateProductionRequestState.Success(productionId))
            } catch (e: Exception) {
                emit(UpdateProductionRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(UpdateProductionRequestState.Loading)
        }
    }
}