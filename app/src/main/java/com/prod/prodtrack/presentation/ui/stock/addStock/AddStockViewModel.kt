package com.prod.prodtrack.presentation.ui.stock.addStock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.domain.model.Stock
import com.prod.domain.usecase.stock.addStock.AddStockRequestState
import com.prod.domain.usecase.stock.addStock.AddStockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddStockViewModel @Inject constructor(
    private val addStockUseCase: AddStockUseCase,
) :
    ViewModel() {
    private val _addStockState: MutableStateFlow<AddStockUiState> =
        MutableStateFlow(AddStockUiState.Idle)
    val addStockState: StateFlow<AddStockUiState> = _addStockState.asStateFlow()

    fun addStock(stock: Stock) {
        viewModelScope.launch {
            addStockUseCase.invoke(stock).collect { addStockRequestState ->
                _addStockState.value = when (addStockRequestState) {
                    is AddStockRequestState.Exception -> AddStockUiState.Exception(
                        code = addStockRequestState.code,
                        exception = addStockRequestState.exception
                    )

                    is AddStockRequestState.Success -> AddStockUiState.Success(
                        data = addStockRequestState.data
                    )

                    is AddStockRequestState.Loading -> AddStockUiState.Loading

                }
            }
        }
    }
}