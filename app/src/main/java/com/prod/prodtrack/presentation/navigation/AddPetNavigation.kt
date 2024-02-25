package com.prod.prodtrack.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.prod.prodtrack.presentation.ui.addPet.AddPetScreen

const val ADD_PET_ROUTE = "addPet"
fun NavGraphBuilder.addPetScreen(
    onNavigateUp: () -> Unit
) {
    composable(
        ADD_PET_ROUTE,
    ) { navBackStackEntry ->
        AddPetScreen(
            onNavigateUp = onNavigateUp
        )
    }
}

fun NavController.navigateToAddPetScreen() {
    navigate(ADD_PET_ROUTE)
}