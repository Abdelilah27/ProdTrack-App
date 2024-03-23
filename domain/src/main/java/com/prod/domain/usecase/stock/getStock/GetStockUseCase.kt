package com.prod.domain.usecase.stock.getStock

import kotlinx.coroutines.flow.Flow


interface GetStockUseCase {
    fun invoke(id: Int): Flow<GetStockRequestState>
}