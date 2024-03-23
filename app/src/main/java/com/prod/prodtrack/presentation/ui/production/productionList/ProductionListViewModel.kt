package com.prod.prodtrack.presentation.ui.production.productionList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.domain.usecase.pet.petList.PetListRequestState
import com.prod.domain.usecase.pet.petList.PetListUseCase
import com.prod.domain.usecase.production.deleteProduction.DeleteProductionRequestState
import com.prod.domain.usecase.production.deleteProduction.DeleteProductionUseCase
import com.prod.domain.usecase.production.productionList.ProductionListRequestState
import com.prod.domain.usecase.production.productionList.ProductionListUseCase
import com.prod.domain.usecase.stock.stockList.StockListRequestState
import com.prod.domain.usecase.stock.stockList.StockListUseCase
import com.prod.prodtrack.presentation.ui.utils.intent.PetListIntent
import com.prod.prodtrack.presentation.ui.utils.intent.ProductionListIntent
import com.prod.prodtrack.presentation.ui.utils.intent.StockListIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductionListViewModel @Inject constructor(
    private val petListUseCase: PetListUseCase,
    private val stockListUseCase: StockListUseCase,
    private val productionListUseCase: ProductionListUseCase,
    private val deleteProductionUseCase: DeleteProductionUseCase,
) : ViewModel() {

    private val _petListState = MutableStateFlow<PetListRequestState>(PetListRequestState.Loading)
    val petListState: StateFlow<PetListRequestState> = _petListState

    private val _stockListState =
        MutableStateFlow<StockListRequestState>(StockListRequestState.Loading)
    val stockListState: StateFlow<StockListRequestState> = _stockListState

    private val _productionListState =
        MutableStateFlow<ProductionListRequestState>(ProductionListRequestState.Loading)
    val productionListState: StateFlow<ProductionListRequestState> = _productionListState

    private val _deleteProductionState =
        MutableStateFlow<DeleteProductionUiState>(DeleteProductionUiState.Loading)
    val deleteProductionState: StateFlow<DeleteProductionUiState> = _deleteProductionState


    init {
        processPetIntent(PetListIntent.PetList)
        processStockIntent(StockListIntent.StockList)
        processProductionIntent(ProductionListIntent.ProductionList)
    }

    fun processPetIntent(intent: PetListIntent) {
        if (intent is PetListIntent.PetList) {
            viewModelScope.launch {
                fetchPets()
            }
        }
    }

    fun processStockIntent(intent: StockListIntent) {
        if (intent is StockListIntent.StockList) {
            viewModelScope.launch {
                fetchStocks()
            }
        }
    }

    fun processProductionIntent(intent: ProductionListIntent) {
        if (intent is ProductionListIntent.ProductionList) {
            viewModelScope.launch {
                fetchProductions()
            }
        }
    }

    private suspend fun fetchPets() {
        viewModelScope.launch {
            try {
                petListUseCase.invoke().collect { petListRequestState ->
                    _petListState.value = petListRequestState
                }
            } catch (e: Exception) {
                _petListState.value = PetListRequestState.Exception(exception = e)
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

    private fun fetchProductions() {
        viewModelScope.launch {
            try {
                productionListUseCase.invoke().collect { productionListRequestState ->
                    _productionListState.value = productionListRequestState
                }
            } catch (e: Exception) {
                _productionListState.value = ProductionListRequestState.Exception(exception = e)
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

}