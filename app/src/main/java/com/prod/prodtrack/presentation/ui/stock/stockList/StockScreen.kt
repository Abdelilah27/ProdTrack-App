package com.prod.prodtrack.presentation.ui.stock.stockList

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
import com.prod.domain.usecase.stock.stockList.StockListRequestState
import com.prod.prodtrack.R
import com.prod.prodtrack.presentation.ui.utils.intent.StockListIntent

@Composable
fun StockScreen(
    stockListViewModel: StockListViewModel = hiltViewModel(),
) {
    val stockListState by stockListViewModel.stockListState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = stockListViewModel) {
        stockListViewModel.processIntent(StockListIntent.StockList)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (val state = stockListState) {
            is StockListRequestState.Loading -> {
                ProgressBar(modifier = Modifier.align(Alignment.Center))
            }

            is StockListRequestState.Success -> {
                StockList(
                    stocks = state.data,
                )
            }

            is StockListRequestState.Exception -> {
                showToast(
                    LocalContext.current,
                    stringResource(id = R.string.failed_to_load_stocks)
                )
            }
        }
    }
}