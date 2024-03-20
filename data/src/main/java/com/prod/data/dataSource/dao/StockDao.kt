package com.prod.data.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.prod.data.entities.StockEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface StockDao {
    @Insert(onConflict = IGNORE)
    suspend fun addStock(stockEntity: StockEntity)

    @Query("SELECT * FROM stock_table ORDER BY id ASC")
    fun getAllStocks(): Flow<List<StockEntity>>

    @Query("UPDATE stock_table SET quantity = :newQuantity WHERE id = :stockId")
    suspend fun updateStockQuantity(stockId: Int, newQuantity: Float)
}
