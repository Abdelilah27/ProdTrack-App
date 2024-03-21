package com.prod.prodtrack.presentation.ui.utils.intent

sealed class ProductionListIntent {
    data object ProductionList : ProductionListIntent()
}