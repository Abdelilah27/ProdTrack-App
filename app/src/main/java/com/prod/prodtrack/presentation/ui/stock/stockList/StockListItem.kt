package com.prod.prodtrack.presentation.ui.stock.stockList

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prod.domain.model.Stock

@Composable
fun StockListItem(
    stock: Stock
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "#${stock.id}",
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f),
            fontSize = 14.sp
        )
        Text(
            text = stock.name,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = stock.quantity.toString(),
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}





