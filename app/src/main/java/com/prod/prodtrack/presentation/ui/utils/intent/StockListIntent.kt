package com.prod.prodtrack.presentation.ui.utils.intent

sealed class StockListIntent {
    data object StockList : StockListIntent()
}