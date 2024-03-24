package com.prod.prodtrack.presentation.ui.production.productionList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prod.domain.model.Production

@Composable
fun ProductionListItem(
    production: Production,
    petName: String,
    stockName: String,
    onProductionClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                production.id?.let { onProductionClicked.invoke(it) }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        
        Text(
            text = petName,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start),
            textAlign = TextAlign.Center
        )

        Text(
            text = stockName,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Text(
            text = production.quantity.toString(),
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Text(
            text = production.producing.toString(),
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Text(
            text = production.date,
            modifier = Modifier
                .weight(3f)
                .wrapContentWidth(Alignment.End),
            textAlign = TextAlign.End
        )
    }
}








