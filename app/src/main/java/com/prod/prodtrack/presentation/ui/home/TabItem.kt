package com.prod.prodtrack.presentation.ui.home

import androidx.compose.runtime.Composable

data class TabItem(
    val title: String,
    val screen: @Composable () -> Unit,
    val onAddButtonClicked: () -> Unit
)