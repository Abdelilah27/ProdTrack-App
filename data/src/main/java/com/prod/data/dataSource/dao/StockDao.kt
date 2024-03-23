package com.prod.data.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.prod.data.entities.PetEntity
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

    @Query("DELETE FROM stock_table WHERE id = :id")
    suspend fun deleteStockById(id: Int)

    @Query("UPDATE stock_table SET name = :name, quantity = :quantity WHERE id = :id")
    suspend fun updateStock(id: Int, name: String, quantity: Float)

    @Query("SELECT * FROM stock_table WHERE id = :id")
    fun getPetStockById(id: Int): Flow<StockEntity>
}
