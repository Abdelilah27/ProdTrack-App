package com.prod.domain.usecase.stock.updateStock

import com.prod.domain.repository.pet.PetRepository
import com.prod.domain.repository.stock.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UpdateStockUseCaseImp @Inject constructor(
    private val repository: StockRepository
) : UpdateStockUseCase {
    // TODO
    override suspend fun invoke(
        id: Int,
        name: String,
        quantity: Float
    ): Flow<UpdateStockRequestState> {
        return flow {
            try {
                repository.updateStock(id, name, quantity)
                emit(UpdateStockRequestState.Success(id))
            } catch (e: Exception) {
                emit(UpdateStockRequestState.Exception(exception = e))
            }
        }.onStart {
            emit(UpdateStockRequestState.Loading)
        }
    }
}