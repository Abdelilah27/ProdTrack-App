package com.prod.prodtrack.presentation.ui.stock.addStock

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.prod.domain.model.Pet
import com.prod.domain.model.Stock
import com.prod.prodtrack.R
import com.prod.prodtrack.presentation.ui.pet.addPet.AddPetUiState

@Composable
fun AddStockScreen(
    onNavigateUp: () -> Unit,
    addStockViewModel: AddStockViewModel = hiltViewModel(),
) {

    val state by addStockViewModel.addStockState.collectAsStateWithLifecycle()

    var stockName by remember { mutableStateOf("") }
    var stockQuantity by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.add_stock),
                hasBackBtn = true,
                onBackBtnClicked = onNavigateUp
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = stockName,
                    onValueChange = { stockName = it },
                    label = { Text(stringResource(id = R.string.stock_name_label)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                OutlinedTextField(
                    value = stockQuantity,
                    onValueChange = { stockQuantity = it },
                    label = { Text(stringResource(id = R.string.stock_quantity_label)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.weight(1f))
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
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(stringResource(id = R.string.save_button))
                }
            }
            // TODO
            when (state) {
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
        }
    }
}