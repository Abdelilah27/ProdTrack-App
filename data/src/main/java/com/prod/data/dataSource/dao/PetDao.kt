package com.prod.data.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import com.prod.data.entities.PetEntity


@Dao
interface PetDao {
    @Insert(onConflict = IGNORE)
    suspend fun addPet(petEntity: PetEntity)
}
