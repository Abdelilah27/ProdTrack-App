package com.prod.data.dataSource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prod.data.dataSource.dao.PetDao
import com.prod.data.entities.PetEntity

@Database(
    entities = [PetEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProdTrackDb : RoomDatabase() {
    abstract val petDao: PetDao
}