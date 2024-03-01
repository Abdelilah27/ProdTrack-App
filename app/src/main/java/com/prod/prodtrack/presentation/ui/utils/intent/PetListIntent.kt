package com.prod.prodtrack.presentation.ui.utils.intent

sealed class PetListIntent {
    data object PetList : PetListIntent()
}