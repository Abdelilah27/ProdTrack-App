package com.prod.prodtrack.presentation.ui.production.addProduction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.prod.common.view.components.AppTopBar
import com.prod.common.view.components.ProgressBar
import com.prod.common.view.components.showToast
import com.prod.prodtrack.R

@Composable
fun AddProductionScreen(
    onNavigateUp: () -> Unit,
    addProductionViewModel: AddProductionViewModel = hiltViewModel(),
) {
    val state by addProductionViewModel.addProductionState.collectAsStateWithLifecycle()
    val petStockListState by addProductionViewModel.petStockListState.collectAsStateWithLifecycle()


    var selectedPetId by remember { mutableStateOf("") }
    var selectedStockId by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var quantityMax by remember { mutableStateOf("") }
    var producing by remember { mutableStateOf("") }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.add_production),
                hasBackBtn = true,
                onBackBtnClicked = onNavigateUp
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {

                PetDropdown(
                    pets = (petStockListState as? PetStockListRequestState.Success)?.petList
                        ?: emptyList(),
                    selectedPetId = selectedPetId.takeIf { it.isNotEmpty() }?.toIntOrNull(),
                    onPetSelected = { selectedPetId = it.toString() }
                )

                Spacer(modifier = Modifier.height(16.dp))

                StockDropdown(
                    stocks = (petStockListState as? PetStockListRequestState.Success)?.stockList
                        ?: emptyList(),
                    selectedStockId = selectedStockId.takeIf { it.isNotEmpty() }?.toIntOrNull(),
                    onStockSelected = { stockId ->
                        selectedStockId = stockId.toString()
                        val selectedStock =
                            (petStockListState as? PetStockListRequestState.Success)?.stockList
                                ?.find { it.id == stockId }
                        quantityMax = (selectedStock?.quantity ?: "").toString()
                    }
                )


                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = {
                        Text(stringResource(id = R.string.quantity_label) + " " + quantityMax)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = producing,
                    onValueChange = { producing = it },
                    label = { Text(stringResource(id = R.string.producing_label)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        addProductionViewModel.addProduction(
                            petId = selectedPetId.toInt(),
                            stockId = selectedStockId.toInt(),
                            quantity = quantity.toFloatOrNull() ?: 0f,
                            newQuantity = quantityMax.toFloat() - quantity.toFloat(),
                            producing = producing.takeIf { it.isNotBlank() }?.toFloatOrNull() ?: 0f
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    enabled = !(selectedPetId.isEmpty() || selectedStockId.isEmpty() ||
                            quantity.isBlank() ||
                            (!(quantity.toFloatOrNull() != null) || (producing.isNotBlank() && producing.toFloatOrNull() == null)) ||
                            (quantity.toFloatOrNull() ?: 0f) > (quantityMax.toFloatOrNull() ?: 0f) ||
                            (quantity.toFloatOrNull() ?: 0f) < 0f ||
                            (producing.isNotBlank() && (producing.toFloatOrNull() ?: 0f) < 0f) ||
                            state is AddProductionUiState.Loading)
                ) {
                    Text(stringResource(id = R.string.save_button))
                }



            }
            // TODO
            when (petStockListState) {
                is PetStockListRequestState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_to_load_pets_stocks)
                    )
                }

                is PetStockListRequestState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is PetStockListRequestState.Success -> {}
            }

            when (state) {
                is AddProductionUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_to_add_production)
                    )
                }

                is AddProductionUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is AddProductionUiState.Success -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.production_added_successfully)
                    )
                    onNavigateUp()
                }

                else -> {}
            }
        }
    }
}