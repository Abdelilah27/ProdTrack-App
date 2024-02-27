package com.prod.data.mapper

interface Mapper<Entity, Domain> {
    fun map(data: Entity): Domain
}