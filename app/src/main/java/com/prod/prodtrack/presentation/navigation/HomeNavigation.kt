package com.prod.prodtrack.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.prod.prodtrack.presentation.ui.home.HomeScreen

const val HOME_ROUTE = "Home"
fun NavGraphBuilder.homeScreen(
    onPetClicked: (Int) -> Unit,
    onStockClicked: (Int) -> Unit,
    onProductionClicked: (Int) -> Unit,
    onAddPetButtonClicked: () -> Unit,
    onAddStockButtonClicked: () -> Unit,
    onAddProductionButtonClicked: () -> Unit
) {
    composable(HOME_ROUTE) {
        HomeScreen(
            onPetClicked = onPetClicked,
            onAddProductionButtonClicked = onAddProductionButtonClicked,
            onAddStockButtonClicked = onAddStockButtonClicked,
            onAddPetButtonClicked = onAddPetButtonClicked
        )
    }
}