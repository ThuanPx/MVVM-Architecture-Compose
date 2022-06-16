@file:OptIn(ExperimentalMaterial3Api::class)

package com.thuanpx.mvvm_architecture_compose.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.thuanpx.mvvm_architecture_compose.base.BaseUiState
import com.thuanpx.mvvm_architecture_compose.base.ui.component.AppGradientBackground
import com.thuanpx.mvvm_architecture_compose.base.ui.component.HandleBaseState
import com.thuanpx.mvvm_architecture_compose.base.ui.theme.Red30
import com.thuanpx.mvvm_architecture_compose.base.ui.theme.Red40

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
    onClick: (name: String) -> Unit = {},
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
        AppGradientBackground {
            if (baseUiState is BaseUiState.Completed) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    columns = GridCells.Fixed(2),
                ) {
                    PokemonState(
                        homePokemonUiState = homePokemonUiState, onClick = onClick
                    )
                }
            } else {
                baseUiState.HandleBaseState()
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
