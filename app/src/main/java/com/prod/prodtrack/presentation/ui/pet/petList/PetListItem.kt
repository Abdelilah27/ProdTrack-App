package com.prod.prodtrack.presentation.ui.pet.petList

import androidx.compose.foundation.clickable
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
import com.prod.domain.model.Pet

@Composable
fun PetListItem(
    pet: Pet,
    onPetClicked: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                pet.id?.let { onPetClicked.invoke(it) }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "#${pet.id}",
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
        Text(
            text = pet.name,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
    }
}



