package com.prod.prodtrack.presentation.ui.production.addProduction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
    productionId: Int? = null
) {
    val addProductionState by addProductionViewModel.addProductionState.collectAsStateWithLifecycle()
    val petStockListState by addProductionViewModel.petStockListState.collectAsStateWithLifecycle()
    val getProductionState by addProductionViewModel.getProductionState.collectAsStateWithLifecycle()
    val deleteProductionState by addProductionViewModel.deleteProductionState.collectAsStateWithLifecycle()
    val updateProductionState by addProductionViewModel.updateProductionState.collectAsStateWithLifecycle()


    var selectedPetId by rememberSaveable { mutableStateOf("") }
    var isProductionPetModified by remember { mutableStateOf(false) }
    var selectedStockId by rememberSaveable { mutableStateOf("") }
    var isProductionStockModified by remember { mutableStateOf(false) }
    var quantity by rememberSaveable { mutableStateOf("") }
    var isProductionQuantityModified by remember { mutableStateOf(false) }
    var quantityMax by rememberSaveable { mutableStateOf("") }
    var isProductionStockQuantityMaxModified by remember { mutableStateOf(false) }
    var producing by rememberSaveable { mutableStateOf("") }
    var isProducingModified by remember { mutableStateOf(false) }


    LaunchedEffect(productionId) {
        if (productionId != null) addProductionViewModel.getProductionById(productionId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = if (productionId == null) stringResource(id = R.string.add_production) else stringResource(
                    id = R.string.edit_production
                ),
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
                    onPetSelected = {
                        selectedPetId = it.toString(); isProductionPetModified = true
                    }
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
                        isProductionStockModified = true
                        isProductionStockQuantityMaxModified = true
                    }
                )


                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it; isProductionQuantityModified = true },
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
                    onValueChange = { producing = it;isProducingModified = true },
                    label = { Text(stringResource(id = R.string.producing_label)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.weight(1f))

                if (productionId == null) {
                    Button(
                        onClick = {
                            addProductionViewModel.addProduction(
                                petId = selectedPetId.toInt(),
                                stockId = selectedStockId.toInt(),
                                quantity = quantity.toFloatOrNull() ?: 0f,
                                newQuantity = quantityMax.toFloat() - quantity.toFloat(),
                                producing = producing.takeIf { it.isNotBlank() }?.toFloatOrNull()
                                    ?: 0f
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        enabled = !(selectedPetId.isEmpty() || selectedStockId.isEmpty() ||
                                quantity.isBlank() ||
                                (!(quantity.toFloatOrNull() != null) || (producing.isNotBlank() && producing.toFloatOrNull() == null)) ||
                                (quantity.toFloatOrNull() ?: 0f) > (quantityMax.toFloatOrNull()
                            ?: 0f) ||
                                (quantity.toFloatOrNull() ?: 0f) < 0f ||
                                (producing.isNotBlank() && (producing.toFloatOrNull()
                                    ?: 0f) < 0f) ||
                                addProductionState is AddProductionUiState.Loading)
                    ) {
                        Text(stringResource(id = R.string.save_button))
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                addProductionViewModel.updateProduction(
                                    productionId = productionId,
                                    petId = selectedPetId.toInt(),
                                    stockId = selectedStockId.toInt(),
                                    producing = producing.toFloatOrNull()?.coerceAtLeast(0f) ?: 0f,
                                    quantity = quantity.toFloat(),
                                    newQuantity = quantityMax.toFloat() - quantity.toFloat()
                                )
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            enabled = (selectedPetId.isNotEmpty() && selectedStockId.isNotEmpty() &&
                                    quantity.isNotBlank() && (quantity.toFloatOrNull() != null) && (quantity.toFloatOrNull()
                                ?: 0f) >= 0 &&
                                    (producing.isBlank() || (producing.isNotBlank() && producing.toFloatOrNull() != null && (producing.toFloatOrNull()
                                        ?: 0f) >= 0)) &&
                                    (quantity.toFloatOrNull() ?: 0f) <= (quantityMax.toFloatOrNull()
                                ?: 0f) &&
                                    updateProductionState !is UpdateProductionUiState.Loading)
                        ) {
                            Text(stringResource(id = R.string.update_button))
                        }

                        Button(
                            onClick = {
                                addProductionViewModel.deleteProduction(productionId)
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp),
                            enabled = deleteProductionState !is DeleteProductionUiState.Loading
                        ) {
                            Text(stringResource(id = R.string.delete_button))
                        }
                    }
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

            when (addProductionState) {
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

            when (deleteProductionState) {
                is DeleteProductionUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_to_delete_production)
                    )
                }

                is DeleteProductionUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is DeleteProductionUiState.Success -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.production_deleted_successfully)
                    )
                    onNavigateUp()
                }

                else -> {}
            }

            when (updateProductionState) {
                is UpdateProductionUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_to_update_production)
                    )
                }

                is UpdateProductionUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is UpdateProductionUiState.Success -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.production_updated_successfully)
                    )
                    onNavigateUp()
                }

                else -> {}
            }

            when (val state = getProductionState) {
                is GetProductionUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_operation)
                    )
                }

                is GetProductionUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is GetProductionUiState.Success -> {
                    if (!isProductionQuantityModified) quantity =
                        state.production.quantity.toString()
                    if (!isProducingModified) producing = state.production.producing.toString()
                    if (!isProductionPetModified) selectedPetId = state.production.petId.toString()
                    if (!isProductionStockModified) selectedStockId =
                        state.production.stockId.toString()
                    if (!isProductionStockQuantityMaxModified) quantityMax =
                        (petStockListState as? PetStockListRequestState.Success)?.stockList
                            ?.find { it.id == state.production.stockId }?.quantity.toString()

                }

                else -> {}
            }
        }
    }
}