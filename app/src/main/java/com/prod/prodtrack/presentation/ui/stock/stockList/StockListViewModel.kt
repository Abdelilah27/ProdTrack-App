package com.prod.prodtrack.presentation.ui.stock.stockList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.domain.usecase.stock.stockList.StockListRequestState
import com.prod.domain.usecase.stock.stockList.StockListUseCase
import com.prod.prodtrack.presentation.ui.utils.intent.StockListIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val stockListUseCase: StockListUseCase,
) : ViewModel() {

    private val _stockListState =
        MutableStateFlow<StockListRequestState>(StockListRequestState.Loading)
    val stockListState: StateFlow<StockListRequestState> = _stockListState


    init {
        processIntent(StockListIntent.StockList)
    }

    fun processIntent(intent: StockListIntent) {
        if (intent is StockListIntent.StockList) {
            viewModelScope.launch {
                fetchStocks()
            }
        }
    }

    private suspend fun fetchStocks() {
        viewModelScope.launch {
            try {
                stockListUseCase.invoke().collect { stockListRequestState ->
                    _stockListState.value = stockListRequestState
                }
            } catch (e: Exception) {
                _stockListState.value = StockListRequestState.Exception(exception = e)
            }
        }
    }
}