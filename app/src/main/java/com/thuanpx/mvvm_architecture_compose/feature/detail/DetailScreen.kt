package com.thuanpx.mvvm_architecture_compose.feature.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thuanpx.mvvm_architecture_compose.base.BaseUiState
import com.thuanpx.mvvm_architecture_compose.base.ui.component.AppGradientBackground
import com.thuanpx.mvvm_architecture_compose.base.ui.component.HandleBaseState
import com.thuanpx.mvvm_architecture_compose.base.ui.theme.Red40

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
    val baseUiState: BaseUiState by viewModel.baseUiState.collectAsState()
    val detailUiState: DetailUiState by viewModel.detailUiState.collectAsState()
    DetailScreen(
        modifier = modifier,
        onClickBack = onClickBack,
        viewModel = viewModel,
        baseUiState = baseUiState,
        detailUiState = detailUiState
    )
}

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(
    baseUiState: BaseUiState,
    viewModel: DetailViewModel,
    detailUiState: DetailUiState,
    onClickBack: () -> Unit,
    modifier: Modifier,
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
            if (baseUiState is BaseUiState.Completed) {
                PokemonInfoState(detailUiState = detailUiState)
            } else {
                baseUiState.HandleBaseState()
            }
        }
    }
}

@Composable
private fun PokemonInfoState(
    detailUiState: DetailUiState
) {
    when (detailUiState) {
        is DetailUiState.Empty -> {}
        is DetailUiState.Success -> {
            Column(
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(detailUiState.pokemonInfo.getImageUrl())
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
                Text(text = detailUiState.pokemonInfo.name ?: "")
            }
        }
    }
}

