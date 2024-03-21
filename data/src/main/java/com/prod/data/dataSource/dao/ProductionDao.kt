package com.prod.data.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.prod.data.entities.ProductionEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductionDao {
    @Insert(onConflict = IGNORE)
    suspend fun addProduction(productionEntity: ProductionEntity)


    @Query("SELECT * FROM production_table ORDER BY id ASC")
    fun getAllProductions(): Flow<List<ProductionEntity>>

}
