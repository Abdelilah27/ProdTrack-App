package com.prod.domain.repository.stock

import com.prod.domain.model.Stock
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun addStock(stock: Stock)
    fun getAllStocks(): Flow<List<Stock>>
    suspend fun updateStockQuantity(stockId: Int, newQuantity: Float)

    suspend fun deleteStockById(id: Int)

}