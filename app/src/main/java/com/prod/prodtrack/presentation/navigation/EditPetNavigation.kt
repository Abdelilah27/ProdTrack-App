package com.prod.prodtrack.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.prod.domain.model.Pet
import com.prod.prodtrack.presentation.ui.pet.addPet.AddPetScreen

const val EDIT_PET_ROUTE = "editPet/{petId}"
const val ARG_PET_ID = "petId"
fun NavGraphBuilder.editPetScreen(
    onNavigateUp: () -> Unit
) {
    composable(
        EDIT_PET_ROUTE,
        arguments = listOf(navArgument(ARG_PET_ID) { type = NavType.IntType })
    ) { navBackStackEntry ->
        AddPetScreen(
            petId = navBackStackEntry.arguments?.getInt(ARG_PET_ID),
            onNavigateUp = onNavigateUp
        )
    }
}

fun NavController.navigateToEditPetScreen(id: Int) {
    navigate("${EDIT_PET_ROUTE.split("/".toRegex()).first()}/$id")
}