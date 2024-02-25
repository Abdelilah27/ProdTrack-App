package com.prod.prodtrack.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.prod.prodtrack.presentation.ui.home.HomeScreen

const val HOME_ROUTE = "Home"
fun NavGraphBuilder.homeScreen(
    onAddPetButtonClicked: () -> Unit
) {
    composable(HOME_ROUTE) {
        HomeScreen(onAddPetButtonClicked = onAddPetButtonClicked)
    }
}