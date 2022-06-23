package com.thuanpx.mvvm_architecture_compose.feature.home

import com.thuanpx.mvvm_architecture_compose.model.entity.Pokemon

/**
 * Created by ThuanPx on 5/25/22.
 */
sealed interface HomePokemonUiState {
    object Empty: HomePokemonUiState
    object Loading: HomePokemonUiState
    data class Error(val error: Throwable) : HomePokemonUiState
    data class PokemonUiState(
        val pokemons: MutableList<Pokemon> = mutableListOf(),
        val isLoading: Boolean = false,
        val isLoadingMore: Boolean = false,
        val endOfChannels: Boolean = false
    ) : HomePokemonUiState
}