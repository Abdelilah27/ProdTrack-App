package com.prod.prodtrack.presentation.ui.stock.stockList

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prod.domain.model.Stock

@Composable
fun StockList(
    stocks: List<Stock>
) {
    LazyColumn(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        items(stocks) { stock ->
            StockListItem(stock)
        }
    }
}