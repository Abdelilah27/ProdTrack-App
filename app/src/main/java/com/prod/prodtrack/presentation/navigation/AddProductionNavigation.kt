package com.prod.prodtrack.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.prod.prodtrack.presentation.ui.production.addProduction.AddProductionScreen

const val ADD_PRODUCTION_ROUTE = "addProduction"
fun NavGraphBuilder.addProductionScreen(
    onNavigateUp: () -> Unit
) {
    composable(
        ADD_PRODUCTION_ROUTE,
    ) { navBackStackEntry ->
        AddProductionScreen(
            onNavigateUp = onNavigateUp
        )
    }
}

fun NavController.navigateToAddProductionScreen() {
    navigate(ADD_PRODUCTION_ROUTE)
}