package com.prod.prodtrack.presentation.ui.production.addProduction

import com.prod.domain.model.Pet
import com.prod.domain.model.Stock

sealed class PetStockListRequestState {
    data class Loading(val petList: List<Pet> = emptyList(), val stockList: List<Stock> = emptyList()) :
        PetStockListRequestState()

    data class Success(val petList: List<Pet>, val stockList: List<Stock>) :
        PetStockListRequestState()

    data class Exception(val code: Int = -1, val exception: Throwable) :
        PetStockListRequestState()
}
