@file:OptIn(ExperimentalMaterial3Api::class)

package com.thuanpx.mvvm_architecture_compose.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.thuanpx.mvvm_architecture_compose.base.BaseUiState
import com.thuanpx.mvvm_architecture_compose.base.ui.component.AppErrorAlertDialog
import com.thuanpx.mvvm_architecture_compose.base.ui.component.AppGradientBackground
import com.thuanpx.mvvm_architecture_compose.base.ui.component.LoadingWheel

/**
 * Created by ThuanPx on 5/20/22.
 */

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val baseUiState: BaseUiState by viewModel.baseUiState.collectAsState()
    val homePokemonUiState: HomePokemonUiState by viewModel.homePokemonUiState.collectAsState()
    HomeScreen(
        baseUiState = baseUiState,
        homePokemonUiState = homePokemonUiState,
        onClick = onClick,
        modifier = modifier,
    )
}

@Composable
fun HomeScreen(
    baseUiState: BaseUiState,
    modifier: Modifier = Modifier,
    homePokemonUiState: HomePokemonUiState,
    onClick: () -> Unit = {},
) {
    Scaffold(modifier = modifier) { innerPadding ->
        AppGradientBackground {
            when (baseUiState) {
                is BaseUiState.Completed -> {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        columns = GridCells.Fixed(2),
                    ) {
                        PokemonState(
                            homePokemonUiState = homePokemonUiState,
                            onClick = onClick
                        )
                    }
                }
                is BaseUiState.Loading -> LoadingWheel(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(),
                    contentDesc = "Home"
                )
                is BaseUiState.Error -> {
                    AppErrorAlertDialog(text = baseUiState.error.toString())
                }
            }
        }
    }
}

private fun LazyGridScope.PokemonState(
    homePokemonUiState: HomePokemonUiState, onClick: () -> Unit
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
