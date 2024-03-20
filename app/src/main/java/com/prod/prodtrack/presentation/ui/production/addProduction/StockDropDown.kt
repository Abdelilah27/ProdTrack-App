package com.prod.prodtrack.presentation.ui.production.addProduction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.prod.domain.model.Stock
import com.prod.prodtrack.R

@Composable
fun StockDropdown(
    stocks: List<Stock>,
    selectedStockId: Int?,
    onStockSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stocks.find { it.id == selectedStockId }?.name
                        ?: stringResource(id = R.string.select_stock)
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                stocks.forEach { stock ->

                    DropdownMenuItem(text = { Text(text = stock.name) }, onClick = {
                        stock.id?.let { onStockSelected(it) }
                        expanded = false
                    })
                }
            }
        }
    }
}