package com.prod.prodtrack.presentation.ui.stock.addStock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.domain.model.Stock
import com.prod.domain.usecase.stock.addStock.AddStockRequestState
import com.prod.domain.usecase.stock.addStock.AddStockUseCase
import com.prod.domain.usecase.stock.deleteStock.DeleteStockRequestState
import com.prod.domain.usecase.stock.deleteStock.DeleteStockUseCase
import com.prod.domain.usecase.stock.getStock.GetStockRequestState
import com.prod.domain.usecase.stock.getStock.GetStockUseCase
import com.prod.domain.usecase.stock.updateStock.UpdateStockRequestState
import com.prod.domain.usecase.stock.updateStock.UpdateStockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddStockViewModel @Inject constructor(
    private val getStockUseCase: GetStockUseCase,
    private val addStockUseCase: AddStockUseCase,
    private val updateStockUseCase: UpdateStockUseCase,
    private val deleteStockUseCase: DeleteStockUseCase,
) :
    ViewModel() {
    private val _addStockState: MutableStateFlow<AddStockUiState> =
        MutableStateFlow(AddStockUiState.Idle)
    val addStockState: StateFlow<AddStockUiState> = _addStockState.asStateFlow()


    private val _deleteStockState = MutableStateFlow<DeleteStockUiState>(DeleteStockUiState.Idle)
    val deleteStockState: StateFlow<DeleteStockUiState> = _deleteStockState.asStateFlow()

    private val _updateStockState = MutableStateFlow<UpdateStockUiState>(UpdateStockUiState.Idle)
    val updateStockState: StateFlow<UpdateStockUiState> = _updateStockState.asStateFlow()

    private val _getStockState = MutableStateFlow<GetStockUiState>(GetStockUiState.Idle)
    val getStockState: StateFlow<GetStockUiState> = _getStockState.asStateFlow()

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

    fun deleteStock(id: Int) {
        viewModelScope.launch {
            deleteStockUseCase.invoke(id).collect { deleteStockRequestState ->
                _deleteStockState.value = when (deleteStockRequestState) {
                    is DeleteStockRequestState.Exception -> DeleteStockUiState.Exception(
                        code = deleteStockRequestState.code,
                        exception = deleteStockRequestState.exception
                    )

                    is DeleteStockRequestState.Success -> DeleteStockUiState.Success(
                        id = deleteStockRequestState.id
                    )

                    is DeleteStockRequestState.Loading -> DeleteStockUiState.Loading

                }
            }
        }
    }


    fun updateStock(id: Int, name: String, quantity: Float) {
        viewModelScope.launch {
            updateStockUseCase.invoke(id, name, quantity).collect { updateStockRequestState ->
                _updateStockState.value = when (updateStockRequestState) {
                    is UpdateStockRequestState.Exception -> UpdateStockUiState.Exception(
                        code = updateStockRequestState.code,
                        exception = updateStockRequestState.exception
                    )

                    is UpdateStockRequestState.Success -> UpdateStockUiState.Success(
                        id
                    )

                    is UpdateStockRequestState.Loading -> UpdateStockUiState.Loading

                }
            }
        }
    }

    fun getStockById(id: Int) {
        viewModelScope.launch {
            getStockUseCase.invoke(id).collect { getStockRequestState ->
                _getStockState.value = when (getStockRequestState) {
                    is GetStockRequestState.Exception -> GetStockUiState.Exception(
                        code = getStockRequestState.code,
                        exception = getStockRequestState.exception
                    )

                    is GetStockRequestState.Success -> GetStockUiState.Success(
                        getStockRequestState.data
                    )

                    is GetStockRequestState.Loading -> GetStockUiState.Loading

                }
            }
        }
    }

}