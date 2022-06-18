package com.thuanpx.mvvm_architecture_compose.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.thuanpx.mvvm_architecture_compose.base.BaseUiState
import com.thuanpx.mvvm_architecture_compose.base.ui.component.AppGradientBackground
import com.thuanpx.mvvm_architecture_compose.base.ui.component.HandleBaseState
import com.thuanpx.mvvm_architecture_compose.base.ui.component.LoadingWheel
import com.thuanpx.mvvm_architecture_compose.base.ui.theme.Red40
import com.thuanpx.mvvm_architecture_compose.model.entity.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * Created by ThuanPx on 5/20/22.
 */

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onClick: (name: String) -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val baseUiState: BaseUiState by viewModel.baseUiState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    HomeScreen(
        isRefreshing = isRefreshing,
        onClick = onClick,
        modifier = modifier,
        pokemonPaging = viewModel.pokemonPaging,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    onClick: (name: String) -> Unit = {},
    pokemonPaging: Flow<PagingData<Pokemon>>,
    viewModel: HomeViewModel
) {
    val lazyPokemonItems = pokemonPaging.collectAsLazyPagingItems()
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
        AppGradientBackground {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                onRefresh = { lazyPokemonItems.refresh() }
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    columns = GridCells.Fixed(2),
                ) {
                    items(lazyPokemonItems.itemCount) {
                        val pokemon = lazyPokemonItems[it] ?: return@items
                        PokemonCard(pokemon = pokemon, onClick = onClick)
                    }
                    lazyPokemonItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item { LoadingWheel() }
                                item { LoadingWheel() }
                            }
                            loadState.append is LoadState.Loading -> {
                                item { LoadingWheel() }
                                item { LoadingWheel() }
                            }
                            loadState.refresh is LoadState.Error -> {
                                val e = lazyPokemonItems.loadState.refresh as LoadState.Error
                                // TODO Error
                            }
                            loadState.append is LoadState.Error -> {
                                val e = lazyPokemonItems.loadState.append as LoadState.Error
                                // TODO Error
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun LazyGridScope.PokemonState(
    homePokemonUiState: HomePokemonUiState, onClick: (name: String) -> Unit
) {
    when (homePokemonUiState) {
        is HomePokemonUiState.Empty -> {

        }
        is HomePokemonUiState.Success -> {
            items(homePokemonUiState.pokemons) { itemPokemon ->
                PokemonCard(pokemon = itemPokemon, onClick = onClick)
            }
        }
    }
}
