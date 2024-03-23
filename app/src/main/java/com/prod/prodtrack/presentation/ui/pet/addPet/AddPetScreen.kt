package com.prod.prodtrack.presentation.ui.pet.addPet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
    petId: Int? = null // Pass the selected pet if editing
) {
    val getPetState by addPetViewModel.getPetState.collectAsStateWithLifecycle()
    val addPetState by addPetViewModel.addPetState.collectAsStateWithLifecycle()
    val deletePetState by addPetViewModel.deletePetState.collectAsStateWithLifecycle()
    val updatePetState by addPetViewModel.updatePetState.collectAsStateWithLifecycle()

    var petName by rememberSaveable { mutableStateOf("") }
    var isPetNameModified by remember { mutableStateOf(false) }

    LaunchedEffect(petId) {
        if (petId != null) addPetViewModel.getPetById(petId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = if (petId == null) stringResource(id = R.string.add_pet) else stringResource(
                    id = R.string.edit_pet
                ),
                hasBackBtn = true,
                onBackBtnClicked = onNavigateUp
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = petName,
                    onValueChange = { petName = it; isPetNameModified = true },
                    label = { Text(stringResource(id = R.string.pet_name_label)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                if (petId == null) {
                    Button(
                        onClick = {
                            addPetViewModel.addPet(Pet(name = petName))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        enabled = petName.isNotBlank() || addPetState !is AddPetUiState.Loading
                    ) {
                        Text(stringResource(id = R.string.save_button))
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                addPetViewModel.updatePet(id = petId, name = petName)
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            enabled = petName.isNotBlank() && updatePetState !is UpdatePetUiState.Loading
                        ) {
                            Text(stringResource(id = R.string.update_button))
                        }

                        Button(
                            onClick = {
                                addPetViewModel.deletePet(petId)
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp),
                            enabled = deletePetState !is DeletePetUiState.Loading
                        ) {
                            Text(stringResource(id = R.string.delete_button))
                        }
                    }


                }
            }

            when (addPetState) {
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
                    onNavigateUp()
                }

                else -> {}
            }

            when (deletePetState) {
                is DeletePetUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_to_delete_pet)
                    )
                }

                is DeletePetUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is DeletePetUiState.Success -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.pet_deleted_successfully)
                    )
                    onNavigateUp()
                }

                else -> {}
            }

            when (updatePetState) {
                is UpdatePetUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_to_update_pet)
                    )
                }

                is UpdatePetUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is UpdatePetUiState.Success -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.pet_updated_successfully)
                    )
                    onNavigateUp()
                }

                else -> {}
            }

            when (val state = getPetState) {
                is GetPetUiState.Exception -> {
                    showToast(
                        LocalContext.current,
                        stringResource(id = R.string.failed_operation)
                    )
                }

                is GetPetUiState.Loading -> {
                    ProgressBar(modifier = Modifier.align(Alignment.Center))
                }

                is GetPetUiState.Success -> {
                    if (!isPetNameModified) petName = state.pet.name
                }

                else -> {}
            }
        }
    }
}