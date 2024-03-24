package com.prod.prodtrack.presentation.ui.production.productionList

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.prod.domain.model.Production
import com.prod.prodtrack.R

@Composable
fun ProductionList(
    productionItems: List<Production>,
    petsMap: Map<Int, String>,
    stocksMap: Map<Int, String>,
    onProductionClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        items(productionItems) { production ->
            ProductionListItem(
                production = production,
                petName = petsMap[production.petId] ?: stringResource(id = R.string.unknown),
                stockName = stocksMap[production.stockId] ?: stringResource(id = R.string.unknown),
                onProductionClicked = onProductionClicked
            )
        }
    }
}