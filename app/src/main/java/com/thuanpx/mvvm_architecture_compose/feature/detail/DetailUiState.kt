package com.thuanpx.mvvm_architecture_compose.feature.detail

import com.thuanpx.mvvm_architecture_compose.model.entity.PokemonInfo

/**
 * Created by ThuanPx on 6/15/22.
 */

sealed interface DetailUiState {
    data class Success(val pokemonInfo: PokemonInfo): DetailUiState
    object Empty: DetailUiState
}