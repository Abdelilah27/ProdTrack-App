package com.prod.prodtrack.presentation.ui.production.productionList

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prod.domain.model.Production

@Composable
fun ProductionList(
    productionItems: List<Production>,
    petsMap: Map<Int, String>,
    stocksMap: Map<Int, String>
) {
    LazyColumn(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        items(productionItems) { production ->
            ProductionListItem(
                production = production,
                petName = petsMap[production.petId] ?: "Unknown",
                stockName = stocksMap[production.stockId] ?: "Unknown"
            )
        }
    }
}