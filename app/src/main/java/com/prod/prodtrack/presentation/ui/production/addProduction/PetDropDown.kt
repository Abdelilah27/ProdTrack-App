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
import com.prod.domain.model.Pet
import com.prod.prodtrack.R

@Composable
fun PetDropdown(
    pets: List<Pet>,
    selectedPetId: Int?,
    onPetSelected: (Int) -> Unit
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
                    text = pets.find { it.id == selectedPetId }?.name
                        ?: stringResource(id = R.string.select_pet)
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                pets.forEach { pet ->
                    DropdownMenuItem(text = { Text(text = pet.name) }, onClick = {
                        pet.id?.let { onPetSelected(it) }
                        expanded = false
                    })
                }
            }


        }
    }
}