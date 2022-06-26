package com.thuanpx.mvvm_architecture_compose.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.thuanpx.mvvm_architecture_compose.base.BaseState
import com.thuanpx.mvvm_architecture_compose.base.ui.component.AppBackground
import com.thuanpx.mvvm_architecture_compose.base.ui.component.EmptyView
import com.thuanpx.mvvm_architecture_compose.base.ui.component.LoadingWheel
import com.thuanpx.mvvm_architecture_compose.base.ui.theme.DarkGreenGray10
import com.thuanpx.mvvm_architecture_compose.base.ui.theme.Red40
import com.thuanpx.mvvm_architecture_compose.feature.home.component.PokemonCard
import com.thuanpx.mvvm_architecture_compose.utils.extension.cast
import com.thuanpx.mvvm_architecture_compose.utils.extension.rememberFlowWithLifecycle

/**
 * Created by ThuanPx on 5/20/22.
 */

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onClick: (name: String) -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        onClick = onClick,
        modifier = modifier,
        viewModel = viewModel,
        uiState = uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    onClick: (name: String) -> Unit = {},
    viewModel: HomeViewModel,
    uiState: BaseState<*>
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Red40
                ),
                title = {
                    Text(text = "Android Architecture", color = Color.White)
                }
            )
        },
        modifier = modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
        ),
    ) { innerPadding ->
        AppBackground(color = DarkGreenGray10) {
            when (uiState) {
                is BaseState.Data -> {
                    val homeState = uiState.cast<BaseState.Data<HomeState>>().value
                    PokemonList(
                        homeState = homeState,
                        onClick = onClick,
                        innerPadding = innerPadding
                    )
                }
                is BaseState.Error -> {

                }
                is BaseState.Loading -> {
                    LoadingWheel()
                }
                is BaseState.Empty -> {
                    EmptyView()
                }
            }
        }
    }
}

@Composable
private fun PokemonList(
    homeState: HomeState,
    onClick: (name: String) -> Unit,
    innerPadding: PaddingValues
) {
    val pokemonPaging =
        rememberFlowWithLifecycle(homeState.pokemonPaging).collectAsLazyPagingItems()
    SwipeRefresh(
        state = rememberSwipeRefreshState(false),
        onRefresh = {
            pokemonPaging.refresh()
        },
        indicatorPadding = innerPadding,
        content = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = innerPadding,
                content = {
                    items(pokemonPaging.itemCount) { index ->
                        pokemonPaging[index]?.let {
                            PokemonCard(pokemon = it, onClick = onClick)
                        }
                    }
                    if (pokemonPaging.loadState.append == LoadState.Loading) {
                        item {
                            LoadingWheel()
                        }
                        item {
                            LoadingWheel()
                        }
                    }
                })
        })
}
