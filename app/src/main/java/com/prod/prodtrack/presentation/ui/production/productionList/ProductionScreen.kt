package com.prod.prodtrack.presentation.ui.production.productionList

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
import com.prod.domain.usecase.pet.petList.PetListRequestState
import com.prod.domain.usecase.production.productionList.ProductionListRequestState
import com.prod.domain.usecase.stock.stockList.StockListRequestState
import com.prod.prodtrack.R
import com.prod.prodtrack.presentation.ui.utils.intent.PetListIntent
import com.prod.prodtrack.presentation.ui.utils.intent.ProductionListIntent
import com.prod.prodtrack.presentation.ui.utils.intent.StockListIntent

@Composable
fun ProductionScreen(
    productionListViewModel: ProductionListViewModel = hiltViewModel(),
    onProductionClicked: (Int) -> Unit
) {
    val productionListState by productionListViewModel.productionListState.collectAsStateWithLifecycle()
    val petListState by productionListViewModel.petListState.collectAsStateWithLifecycle()
    val stockListState by productionListViewModel.stockListState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = productionListViewModel) {
        productionListViewModel.processPetIntent(PetListIntent.PetList)
        productionListViewModel.processStockIntent(StockListIntent.StockList)
        productionListViewModel.processProductionIntent(ProductionListIntent.ProductionList)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (val state = productionListState) {
            is ProductionListRequestState.Loading -> {
                ProgressBar(modifier = Modifier.align(Alignment.Center))
            }

            is ProductionListRequestState.Success -> {
                val petMap = (petListState as? PetListRequestState.Success)?.data?.associateBy(
                    { it.id ?: -1 },
                    { it.name }
                ) ?: emptyMap()

                val stockMap =
                    (stockListState as? StockListRequestState.Success)?.data?.associateBy(
                        { it.id ?: -1 },
                        { it.name }
                    ) ?: emptyMap()
                ProductionList(
                    productionItems = state.data,
                    petsMap = petMap,
                    stocksMap = stockMap,
                    onProductionClicked = onProductionClicked
                )
            }

            is ProductionListRequestState.Exception -> {
                showToast(
                    LocalContext.current,
                    stringResource(id = R.string.failed_to_load_productions)
                )
            }
        }
    }
}