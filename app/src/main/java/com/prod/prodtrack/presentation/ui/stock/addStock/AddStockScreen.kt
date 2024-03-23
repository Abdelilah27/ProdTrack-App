package com.prod.prodtrack.presentation.ui.stock.addStock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.prod.common.view.components.AppTopBar
import com.prod.common.view.components.ProgressBar
import com.prod.common.view.components.showToast
import com.prod.domain.model.Stock
import com.prod.prodtrack.R

@Composable
fun AddStockScreen(
    onNavigateUp: () -> Unit,
    addStockViewModel: AddStockViewModel = hiltViewModel(),
    stockId: Int? = null
) {

    val addStockState by addStockViewModel.addStockState.collectAsStateWithLifecycle()
    val getStockState by addStockViewModel.getStockState.collectAsStateWithLifecycle()
    val deleteStockState by addStockViewModel.deleteStockState.collectAsStateWithLifecycle()
    val updateStockState by addStockViewModel.updateStockState.collectAsStateWithLifecycle()

    var stockName by rememberSaveable { mutableStateOf("") }
    var isStockNameModified by remember { mutableStateOf(false) }
    var stockQuantity by remember { mutableStateOf("") }
    var isStockQuantityModified by remember { mutableStateOf(false) }

    LaunchedEffect(stockId) {
        if (stockId != null) addStockViewModel.getStockById(stockId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = if (stockId == null) stringResource(id = R.string.add_stock) else stringResource(
                    id = R.string.edit_stock
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
                OutlinedTextField(
                    value = stockName,
                    onValueChange = { stockName = it; isStockNameModified = true },
                    label = { Text(stringResource(id = R.string.stock_name_label)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                OutlinedTextField(
                    value = stockQuantity,
                    onValueChange = { stockQuantity = it; isStockQuantityModified = true },
                    label = { Text(stringResource(id = R.string.stock_quantity_label)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.weight(1f))

                if (stockId == null) {
                    Button(
                        onClick = {
                            addStockViewModel.addStock(
                                Stock(
                                    name = stockName,
                                    quantity = stockQuantity.toFloat()
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        enabled = stockName.isNotBlank() && stockQuantity.isNotBlank() // TODO
                                && stockQuantity.toFloatOrNull() != null && stockQuantity.isDigitsOnly()
                                && addStockState !is AddStockUiState.Loading
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
                                addStockViewModel.updateStock(
                                    id = stockId,
                                    name = stockName,
                                    quantity = stockQuantity.toFloat()
                                )
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            enabled = stockName.isNotBlank() && stockQuantity.isNotBlank() && stockQuantity.toFloatOrNull() != null && stockQuantity.isDigitsOnly() && updateStockState !is UpdateStockUiState.Loading
                        ) {
                            Text(stringResource(id = R.string.update_button))
                        }

                        Button(
                            onClick = {
                                addStockViewModel.deleteStock(stockId)
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp),
                            enabled = deleteStockState !is DeleteStockUiState.Loading
                        ) {
                            Text(stringResource(id = R.string.delete_button))
                        }
                    }
                }
            }
            // TODO
            when (addStockState) {
                is AddStockUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_to_add_stock)
                    )
                }

                is AddStockUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is AddStockUiState.Success -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.stock_added_successfully)
                    )
                    onNavigateUp()
                }

                else -> {}
            }

            when (deleteStockState) {
                is DeleteStockUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_to_delete_pet)
                    )
                }

                is DeleteStockUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is DeleteStockUiState.Success -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.stock_deleted_successfully)
                    )
                    onNavigateUp()
                }

                else -> {}
            }

            when (updateStockState) {
                is UpdateStockUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_to_update_stock)
                    )
                }

                is UpdateStockUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is UpdateStockUiState.Success -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.stock_updated_successfully)
                    )
                    onNavigateUp()
                }

                else -> {}
            }

            when (val state = getStockState) {
                is GetStockUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_operation)
                    )
                }

                is GetStockUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is GetStockUiState.Success -> {
                    if (!isStockNameModified) stockName = state.stock.name
                    if (!isStockQuantityModified) stockQuantity = state.stock.quantity.toString()
                }

                else -> {}
            }
        }
    }
}