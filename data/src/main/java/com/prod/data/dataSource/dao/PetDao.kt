package com.prod.data.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
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

    @Query("UPDATE pet_table SET name = :name WHERE id = :id")
    suspend fun updatePet(id: Int, name: String)

    @Query("SELECT * FROM pet_table WHERE id = :id")
    fun getPetPetById(id: Int): Flow<PetEntity>
}
