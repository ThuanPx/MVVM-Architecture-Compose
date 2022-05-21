package com.thuanpx.mvvm_architecture_compose.feature.home

import com.thuanpx.mvvm_architecture_compose.model.entity.Pokemon

/**
 * Created by ThuanPx on 5/25/22.
 */
sealed interface HomePokemonUiState {
    data class Success(val pokemons: List<Pokemon>) : HomePokemonUiState
    object Empty: HomePokemonUiState
}