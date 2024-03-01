package com.prod.data.mapper

import com.prod.data.entities.PetEntity
import com.prod.domain.model.Pet

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