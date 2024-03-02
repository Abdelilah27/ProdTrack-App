package com.prod.prodtrack.presentation.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.prod.prodtrack.presentation.navigation.HOME_ROUTE
import com.prod.prodtrack.presentation.navigation.addPetScreen
import com.prod.prodtrack.presentation.navigation.addProductionScreen
import com.prod.prodtrack.presentation.navigation.addStockScreen
import com.prod.prodtrack.presentation.navigation.homeScreen
import com.prod.prodtrack.presentation.navigation.navigateToAddPetScreen
import com.prod.prodtrack.presentation.navigation.navigateToAddProductionScreen
import com.prod.prodtrack.presentation.navigation.navigateToAddStockScreen

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = HOME_ROUTE
    ) {
        homeScreen(
            onAddPetButtonClicked = { navController.navigateToAddPetScreen() },
            onAddStockButtonClicked = { navController.navigateToAddStockScreen() },
            onAddProductionButtonClicked = { navController.navigateToAddProductionScreen() }
        )
        addPetScreen(onNavigateUp = {
            navController.navigateUp()
        })
        addStockScreen(onNavigateUp = {
            navController.navigateUp()
        })
        addProductionScreen(onNavigateUp = {
            navController.navigateUp()
        })
    }
}