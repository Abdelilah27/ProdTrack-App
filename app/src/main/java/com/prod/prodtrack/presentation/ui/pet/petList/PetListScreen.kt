package com.prod.prodtrack.presentation.ui.pet.petList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.prod.common.view.components.ProgressBar
import com.prod.common.view.components.showToast
import com.prod.domain.model.Pet
import com.prod.domain.usecase.pet.petList.PetListRequestState
import com.prod.prodtrack.R
import com.prod.prodtrack.presentation.ui.utils.intent.PetListIntent

@Composable
fun PetScreen(
    petListViewModel: PetListViewModel = hiltViewModel(),
    onPetClicked: (Int) -> Unit,) {
    val petListState by petListViewModel.petListState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = petListViewModel) {
        petListViewModel.processIntent(PetListIntent.PetList)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (val state = petListState) {
            is PetListRequestState.Loading -> {
                ProgressBar(modifier = Modifier.align(Alignment.Center))
            }

            is PetListRequestState.Success -> {
                PetList(
                    pets = state.data, onPetClicked = onPetClicked
                )
            }

            is PetListRequestState.Exception -> {
                showToast(
                    LocalContext.current,
                    stringResource(id = R.string.failed_to_load_pets)
                )
            }
        }
    }
}

