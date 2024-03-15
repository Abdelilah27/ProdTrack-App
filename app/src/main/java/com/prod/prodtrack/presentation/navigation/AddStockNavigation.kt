package com.prod.prodtrack.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.prod.prodtrack.presentation.ui.stock.addStock.AddStockScreen

const val ADD_STOCK_ROUTE = "addStock"
fun NavGraphBuilder.addStockScreen(
    onNavigateUp: () -> Unit
) {
    composable(
        ADD_STOCK_ROUTE,
    ) { navBackStackEntry ->
        AddStockScreen(
            onNavigateUp = onNavigateUp
        )
    }
}

fun NavController.navigateToAddStockScreen() {
    navigate(ADD_STOCK_ROUTE)
}