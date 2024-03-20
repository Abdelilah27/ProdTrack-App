package com.prod.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prod.data.dataSource.core.Constants.Companion.PET_TABLE
import com.prod.data.dataSource.core.Constants.Companion.PRODUCTION_TABLE


@Entity(tableName = PRODUCTION_TABLE)
data class ProductionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val petId: Int,
    val stockId: Int,
    val date: String,
    val producing: Float? = 0f,
    val quantity: Float
)