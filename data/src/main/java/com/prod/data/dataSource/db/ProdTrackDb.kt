package com.prod.data.dataSource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prod.data.dataSource.dao.PetDao
import com.prod.data.dataSource.dao.ProductionDao
import com.prod.data.dataSource.dao.StockDao
import com.prod.data.entities.PetEntity
import com.prod.data.entities.ProductionEntity
import com.prod.data.entities.StockEntity

@Database(
    entities = [PetEntity::class, StockEntity::class, ProductionEntity::class],
    version = 5,
    exportSchema = false
)
abstract class ProdTrackDb : RoomDatabase() {
    abstract val petDao: PetDao
    abstract val stockDao: StockDao
    abstract val productionDao: ProductionDao
}