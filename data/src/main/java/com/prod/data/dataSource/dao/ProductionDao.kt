package com.prod.data.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.prod.data.entities.ProductionEntity
import com.prod.data.entities.StockEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductionDao {
    @Insert(onConflict = IGNORE)
    suspend fun addProduction(productionEntity: ProductionEntity)

    @Query("SELECT * FROM production_table ORDER BY id ASC")
    fun getAllProductions(): Flow<List<ProductionEntity>>

    @Query("DELETE FROM production_table WHERE id = :id")
    suspend fun deleteProductionById(id: Int)

    @Query("UPDATE production_table SET petId = :petId, stockId = :stockId, date = :date, producing = :producing, quantity = :quantity WHERE id = :productionId")
    suspend fun updateProduction(
        productionId: Int,
        petId: Int,
        stockId: Int,
        date: String,
        producing: Float,
        quantity: Float
    )

    @Query("SELECT * FROM production_table WHERE id = :id")
    fun getPetProductionById(id: Int): Flow<ProductionEntity>

}
