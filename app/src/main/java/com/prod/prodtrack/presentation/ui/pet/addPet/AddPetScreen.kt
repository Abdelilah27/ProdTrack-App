package com.prod.prodtrack.presentation.ui.pet.addPet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.prod.common.view.components.AppTopBar
import com.prod.common.view.components.ProgressBar
import com.prod.common.view.components.showToast
import com.prod.domain.model.Pet
import com.prod.prodtrack.R

@Composable
fun AddPetScreen(
    onNavigateUp: () -> Unit,
    addPetViewModel: AddPetViewModel = hiltViewModel(),
) {
    val state by addPetViewModel.addPetState.collectAsStateWithLifecycle()

    var petName by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.add_pet),
                hasBackBtn = true,
                onBackBtnClicked = onNavigateUp
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = petName,
                    onValueChange = { petName = it },
                    label = { Text(stringResource(id = R.string.pet_name_label)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        addPetViewModel.addPet(Pet(name = petName))
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
                is AddPetUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_to_add_pet)
                    )
                }

                is AddPetUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is AddPetUiState.Success -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.pet_added_successfully)
                    )
                }

                else -> {}
            }
        }
    }
}



