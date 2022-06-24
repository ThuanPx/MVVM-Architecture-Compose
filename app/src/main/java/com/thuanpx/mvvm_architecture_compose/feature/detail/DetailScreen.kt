package com.thuanpx.mvvm_architecture_compose.feature.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thuanpx.mvvm_architecture_compose.base.BaseState
import com.thuanpx.mvvm_architecture_compose.base.ui.component.AppGradientBackground
import com.thuanpx.mvvm_architecture_compose.base.ui.theme.AppTheme
import com.thuanpx.mvvm_architecture_compose.base.ui.theme.Red40
import com.thuanpx.mvvm_architecture_compose.model.entity.PokemonInfo
import com.thuanpx.mvvm_architecture_compose.utils.extension.cast

/**
 * Created by ThuanPx on 5/20/22.
 */

@ExperimentalMaterial3Api
@Composable
fun DetailRoute(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    DetailScreen(
        modifier = modifier,
        onClickBack = onClickBack,
        uiState = uiState
    )
}

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(
    onClickBack: () -> Unit,
    modifier: Modifier,
    uiState: BaseState<*>
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Red40
                ),
                title = {
                    Text(text = "Detail", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        },
        modifier = modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
        ),
    ) { innerPadding ->
        AppGradientBackground(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (uiState) {
                is BaseState.Data -> {
                    val pokemonInfo = uiState.cast<BaseState.Data<DetailState>>().value.pokemonInfo
                    PokemonInfoView(pokemonInfo = pokemonInfo ?: return@AppGradientBackground)
                }
            }
        }
    }
}

@Composable
private fun PokemonInfoView(
    pokemonInfo: PokemonInfo
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemonInfo.getImageUrl())
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}

@Composable
@Preview
private fun PokemonInfoPreview() {
    AppTheme(darkTheme = false) {
        AppGradientBackground {
            PokemonInfoView(
                PokemonInfo(
                    id = 184, name = "Salamence mega"
                )
            )
        }
    }
}

