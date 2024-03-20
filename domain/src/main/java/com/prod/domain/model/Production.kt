package com.prod.domain.model

data class Production(
    val id: Int? = null,
    val petId: Int,
    val stockId: Int,
    val date: String,
    val producing: Float? = 0f,
    val quantity: Float
)