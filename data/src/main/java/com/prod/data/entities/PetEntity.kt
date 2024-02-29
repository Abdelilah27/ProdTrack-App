package com.prod.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prod.data.dataSource.core.Constants.Companion.PET_TABLE


@Entity(tableName = PET_TABLE)
data class PetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)