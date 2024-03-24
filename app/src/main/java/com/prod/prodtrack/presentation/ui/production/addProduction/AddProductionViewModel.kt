package com.prod.prodtrack.presentation.ui.production.addProduction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.domain.model.Production
import com.prod.domain.usecase.pet.petList.PetListRequestState
import com.prod.domain.usecase.pet.petList.PetListUseCase
import com.prod.domain.usecase.production.addProduction.AddProductionRequestState
import com.prod.domain.usecase.production.addProduction.AddProductionUseCase
import com.prod.domain.usecase.production.deleteProduction.DeleteProductionRequestState
import com.prod.domain.usecase.production.deleteProduction.DeleteProductionUseCase
import com.prod.domain.usecase.production.getProduction.GetProductionRequestState
import com.prod.domain.usecase.production.getProduction.GetProductionUseCase
import com.prod.domain.usecase.production.updateProduction.UpdateProductionRequestState
import com.prod.domain.usecase.production.updateProduction.UpdateProductionUseCase
import com.prod.domain.usecase.stock.stockList.StockListRequestState
import com.prod.domain.usecase.stock.stockList.StockListUseCase
import com.prod.domain.usecase.stock.updateQuantityStock.UpdateQuantityStockRequestState
import com.prod.domain.usecase.stock.updateQuantityStock.UpdateQuantityStockUseCase
import com.prod.prodtrack.presentation.ui.utils.getCurrentDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddProductionViewModel @Inject constructor(
    private val getProductionUseCase: GetProductionUseCase,
    private val addProductionUseCase: AddProductionUseCase,
    private val petListUseCase: PetListUseCase,
    private val stockListUseCase: StockListUseCase,
    private val updateQuantityStockUseCase: UpdateQuantityStockUseCase,
    private val deleteProductionUseCase: DeleteProductionUseCase,
    private val updateProductionUseCase: UpdateProductionUseCase,
) :
    ViewModel() {
    private val _addProductionState: MutableStateFlow<AddProductionUiState> =
        MutableStateFlow(AddProductionUiState.Idle)
    val addProductionState: StateFlow<AddProductionUiState> = _addProductionState.asStateFlow()

    private val _updateQuantityStockState: MutableStateFlow<UpdateQuantityStockUiState> =
        MutableStateFlow(UpdateQuantityStockUiState.Idle)
    val updateQuantityStockState: StateFlow<UpdateQuantityStockUiState> =
        _updateQuantityStockState.asStateFlow()

    private val _petStockListState =
        MutableStateFlow<PetStockListRequestState>(PetStockListRequestState.Loading())
    val petStockListState: StateFlow<PetStockListRequestState> = _petStockListState

    private val _deleteProductionState =
        MutableStateFlow<DeleteProductionUiState>(DeleteProductionUiState.Idle)
    val deleteProductionState: StateFlow<DeleteProductionUiState> = _deleteProductionState


    private val _updateProductionState =
        MutableStateFlow<UpdateProductionUiState>(UpdateProductionUiState.Idle)
    val updateProductionState: StateFlow<UpdateProductionUiState> =
        _updateProductionState.asStateFlow()

    private val _getProductionState =
        MutableStateFlow<GetProductionUiState>(GetProductionUiState.Idle)
    val getProductionState: StateFlow<GetProductionUiState> = _getProductionState.asStateFlow()

    init {
        fetchPetAndStockLists()
    }

    private fun fetchPetAndStockLists() {
        viewModelScope.launch {
            combine(
                petListUseCase.invoke(),
                stockListUseCase.invoke()
            ) { petListResult, stockListResult ->
                when {
                    petListResult is PetListRequestState.Success && stockListResult is StockListRequestState.Success -> {
                        PetStockListRequestState.Success(
                            petList = petListResult.data,
                            stockList = stockListResult.data
                        )
                    }

                    petListResult is PetListRequestState.Loading || stockListResult is StockListRequestState.Loading -> {
                        PetStockListRequestState.Loading()
                    }

                    else -> {
                        PetStockListRequestState.Exception(exception = Exception())
                    }
                }
            }.catch { e ->
                _petStockListState.value = PetStockListRequestState.Exception(exception = e)
            }.collect { combinedState ->
                _petStockListState.value = combinedState
            }
        }
    }


    fun addProduction(
        petId: Int,
        stockId: Int,
        quantity: Float,
        newQuantity: Float,
        producing: Float
    ) {
        viewModelScope.launch {
            val production =
                Production(
                    petId = petId,
                    stockId = stockId,
                    date = getCurrentDateTime(),
                    quantity = quantity,
                    producing = producing
                )
            addProductionUseCase.invoke(production).collect { addProductionRequestState ->
                _addProductionState.value = when (addProductionRequestState) {
                    is AddProductionRequestState.Exception -> AddProductionUiState.Exception(
                        code = addProductionRequestState.code,
                        exception = addProductionRequestState.exception
                    )

                    is AddProductionRequestState.Success -> {
                        updateStockQuantity(stockId = stockId, quantity = newQuantity)
                        AddProductionUiState.Success(
                            data = addProductionRequestState.data
                        )
                    }

                    is AddProductionRequestState.Loading -> AddProductionUiState.Loading
                }
            }
        }
    }

    private fun updateStockQuantity(stockId: Int, quantity: Float) {
        viewModelScope.launch {
            updateQuantityStockUseCase.invoke(stockId, quantity)
                .collect { updateQuantityStockState ->
                    _updateQuantityStockState.value = when (updateQuantityStockState) {
                        is UpdateQuantityStockRequestState.Exception -> {
                            // TODO rollback()
                            UpdateQuantityStockUiState.Exception(
                                code = updateQuantityStockState.code,
                                exception = updateQuantityStockState.exception
                            )
                        }

                        is UpdateQuantityStockRequestState.Success -> {
                            UpdateQuantityStockUiState.Success(
                                updateQuantityStockState.stockId
                            )
                        }

                        is UpdateQuantityStockRequestState.Loading -> UpdateQuantityStockUiState.Loading
                    }
                }
        }
    }

    fun deleteProduction(id: Int) {
        viewModelScope.launch {
            deleteProductionUseCase.invoke(id).collect { deleteProductionRequestState ->
                _deleteProductionState.value = when (deleteProductionRequestState) {
                    is DeleteProductionRequestState.Exception -> DeleteProductionUiState.Exception(
                        code = deleteProductionRequestState.code,
                        exception = deleteProductionRequestState.exception
                    )

                    is DeleteProductionRequestState.Success -> DeleteProductionUiState.Success(
                        id = deleteProductionRequestState.id
                    )

                    is DeleteProductionRequestState.Loading -> DeleteProductionUiState.Loading

                }
            }
        }
    }

    fun updateProduction(
        productionId: Int,
        petId: Int,
        stockId: Int,
        producing: Float,
        quantity: Float,
        newQuantity: Float
    ) {
        viewModelScope.launch {
            updateProductionUseCase.invoke(
                productionId = productionId,
                petId = petId,
                stockId = stockId,
                date = getCurrentDateTime(),
                producing = producing,
                quantity = quantity
            )
                .collect { updateProductionRequestState ->
                    _updateProductionState.value = when (updateProductionRequestState) {
                        is UpdateProductionRequestState.Exception -> UpdateProductionUiState.Exception(
                            code = updateProductionRequestState.code,
                            exception = updateProductionRequestState.exception
                        )

                        is UpdateProductionRequestState.Success -> {
                            updateStockQuantity(stockId = stockId, quantity = newQuantity)
                            UpdateProductionUiState.Success(
                                id = productionId
                            )
                        }

                        is UpdateProductionRequestState.Loading -> UpdateProductionUiState.Loading

                    }
                }
        }
    }

    fun getProductionById(id: Int) {
        viewModelScope.launch {
            getProductionUseCase.invoke(id).collect { getProductionRequestState ->
                _getProductionState.value = when (getProductionRequestState) {
                    is GetProductionRequestState.Exception -> GetProductionUiState.Exception(
                        code = getProductionRequestState.code,
                        exception = getProductionRequestState.exception
                    )

                    is GetProductionRequestState.Success -> GetProductionUiState.Success(
                        getProductionRequestState.data
                    )

                    is GetProductionRequestState.Loading -> GetProductionUiState.Loading

                }
            }
        }
    }
}