package com.prod.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prod.data.dataSource.core.Constants.Companion.STOCK_TABLE


@Entity(tableName = STOCK_TABLE)
data class StockEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: Float,
)