package com.prod.prodtrack.presentation.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.prod.common.view.components.AppTopBar
import com.prod.prodtrack.R
import com.prod.prodtrack.presentation.ui.pet.petList.PetScreen
import com.prod.prodtrack.presentation.ui.production.ProductionScreen
import com.prod.prodtrack.presentation.ui.stock.stockList.StockScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onAddPetButtonClicked: () -> Unit,
    onAddStockButtonClicked: () -> Unit,
    onAddProductionButtonClicked: () -> Unit
) {
    val tabItems = listOf(
        TabItem(stringResource(id = R.string.production), { ProductionScreen() }, onAddProductionButtonClicked),
        TabItem(stringResource(id = R.string.stock), { StockScreen() }, onAddStockButtonClicked),
        TabItem(stringResource(id = R.string.pet), { PetScreen() }, onAddPetButtonClicked)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(title = stringResource(id = R.string.app_name))
        }
    ) { paddingValues ->
        var selectedTabIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        val pagerState = rememberPagerState {
            tabItems.size
        }
        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress) {
                selectedTabIndex = pagerState.currentPage
            }
        }
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabItems.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedTabIndex, onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Text(text = item.title)
                        }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.Top,
            ) { index ->
                tabItems[index].screen()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = {
                    tabItems[selectedTabIndex].onAddButtonClicked()
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.add))
            }
        }
    }
}

