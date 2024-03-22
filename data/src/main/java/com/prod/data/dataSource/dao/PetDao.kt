package com.prod.data.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.prod.data.entities.PetEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PetDao {
    @Insert(onConflict = IGNORE)
    suspend fun addPet(petEntity: PetEntity)

    @Query("SELECT * FROM pet_table ORDER BY id ASC")
    fun getAllPets(): Flow<List<PetEntity>>

    @Query("DELETE FROM pet_table WHERE id = :id")
    suspend fun deletePetById(id: Int)
}
