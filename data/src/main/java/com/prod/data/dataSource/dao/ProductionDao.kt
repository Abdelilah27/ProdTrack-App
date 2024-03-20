package com.prod.data.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import com.prod.data.entities.PetEntity
import com.prod.data.entities.ProductionEntity


@Dao
interface ProductionDao {
    @Insert(onConflict = IGNORE)
    suspend fun addProduction(productionEntity: ProductionEntity)

    /*
    @Query("SELECT * FROM pet_table ORDER BY id ASC")
    fun getAllPets(): Flow<List<PetEntity>>
     */
}
