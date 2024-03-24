package com.prod.prodtrack.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.prod.prodtrack.presentation.ui.stock.addStock.AddStockScreen

const val EDIT_STOCK_ROUTE = "editStock/{stockId}"
const val ARG_STOCK_ID = "stockId"
fun NavGraphBuilder.editStockScreen(
    onNavigateUp: () -> Unit
) {
    composable(
        EDIT_STOCK_ROUTE,
        arguments = listOf(navArgument(ARG_STOCK_ID) { type = NavType.IntType })
    ) { navBackStackEntry ->
        AddStockScreen(
            stockId = navBackStackEntry.arguments?.getInt(ARG_STOCK_ID),
            onNavigateUp = onNavigateUp
        )
    }
}

fun NavController.navigateToEditStockScreen(id: Int) {
    navigate("${EDIT_STOCK_ROUTE.split("/".toRegex()).first()}/$id")
}