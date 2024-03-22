package com.prod.data.repository.stock

import com.prod.data.dataSource.dao.StockDao
import com.prod.data.mapper.EntityStockMapper
import com.prod.data.mapper.StockMapper
import com.prod.data.mapper.mapList
import com.prod.domain.model.Stock
import com.prod.domain.repository.stock.StockRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockRepositoryImp @Inject constructor(private val stockDao: StockDao) : StockRepository {
    override suspend fun addStock(stock: Stock) {
        stockDao.addStock(StockMapper.map(stock))
    }

    override fun getAllStocks(): Flow<List<Stock>> {
        return stockDao.getAllStocks().mapList { stockEntity ->
            EntityStockMapper.map(stockEntity)
        }
    }

    override suspend fun updateStockQuantity(stockId: Int, newQuantity: Float) {
        stockDao.updateStockQuantity(stockId = stockId, newQuantity = newQuantity)
    }

    override suspend fun deleteStockById(id: Int) {
        stockDao.deleteStockById(id)
    }
}