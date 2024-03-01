package com.prod.prodtrack.presentation.ui.pet.petList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.prod.common.view.components.ProgressBar
import com.prod.common.view.components.showToast
import com.prod.domain.usecase.pet.petList.PetListRequestState
import com.prod.prodtrack.R

@Composable
fun PetScreen(
    petListViewModel: PetListViewModel = hiltViewModel(),
    onAddPetButtonClicked: () -> Unit,
) {
    val petListState by petListViewModel.petListState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (val state = petListState) {
            is PetListRequestState.Loading -> {
                ProgressBar(modifier = Modifier.fillMaxSize())
            }

            is PetListRequestState.Success -> {
                PetList(
                    pets = state.data,
                )
            }

            is PetListRequestState.Exception -> {
                showToast(
                    LocalContext.current,
                    stringResource(id = R.string.failed_to_load_pets)
                )
            }
        }
        FloatingActionButton(
            onClick = { onAddPetButtonClicked() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}

