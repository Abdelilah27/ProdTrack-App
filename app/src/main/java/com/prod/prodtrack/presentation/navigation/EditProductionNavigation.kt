package com.prod.prodtrack.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.prod.prodtrack.presentation.ui.production.addProduction.AddProductionScreen

const val EDIT_PRODUCTION_ROUTE = "editProduction/{productionId}"
const val ARG_PRODUCTION_ID = "productionId"
fun NavGraphBuilder.editProductionScreen(
    onNavigateUp: () -> Unit
) {
    composable(
        EDIT_PRODUCTION_ROUTE,
        arguments = listOf(navArgument(ARG_PRODUCTION_ID) { type = NavType.IntType })
    ) { navBackStackEntry ->
        AddProductionScreen(
            productionId = navBackStackEntry.arguments?.getInt(ARG_PRODUCTION_ID),
            onNavigateUp = onNavigateUp
        )
    }
}

fun NavController.navigateToEditProductionScreen(id: Int) {
    navigate("${EDIT_PRODUCTION_ROUTE.split("/".toRegex()).first()}/$id")
}