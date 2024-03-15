package com.prod.data.mapper

import com.prod.data.entities.PetEntity
import com.prod.data.entities.StockEntity
import com.prod.domain.model.Pet
import com.prod.domain.model.Stock

object PetMapper : Mapper<Pet, PetEntity> {
    override fun map(data: Pet): PetEntity = PetEntity(
        name = data.name,
    )
}

object EntityPetMapper : Mapper<PetEntity, Pet> {
    override fun map(data: PetEntity): Pet = Pet(
        id = data.id,
        name = data.name,
    )
}

object StockMapper : Mapper<Stock, StockEntity> {
    override fun map(data: Stock): StockEntity = StockEntity(
        name = data.name,
        quantity = data.quantity
    )
}

object EntityStockMapper : Mapper<StockEntity, Stock> {
    override fun map(data: StockEntity): Stock = Stock(
        id = data.id,
        name = data.name,
        quantity = data.quantity
    )
}