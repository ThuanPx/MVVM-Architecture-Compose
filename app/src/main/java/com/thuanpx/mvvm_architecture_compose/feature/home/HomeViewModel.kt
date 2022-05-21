package com.thuanpx.mvvm_architecture_compose.feature.home

import androidx.lifecycle.viewModelScope
import com.thuanpx.mvvm_architecture_compose.base.BaseViewModel
import com.thuanpx.mvvm_architecture_compose.data.repository.AppRepository
import com.thuanpx.mvvm_architecture_compose.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ThuanPx on 5/21/22.
 */


@HiltViewModel
class HomeViewModel @Inject constructor (
    private val appRepository: AppRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : BaseViewModel() {

    init {
        fetchPokemons(1)
    }

    private val _pageState = MutableStateFlow(1)
    val pageState: StateFlow<Int> = _pageState.asStateFlow()

    private val _homePokemonUiState = MutableStateFlow<HomePokemonUiState>(HomePokemonUiState.Empty)
    val homePokemonUiState: StateFlow<HomePokemonUiState> = _homePokemonUiState.asStateFlow()


    fun fetchPokemons(page: Int) {
        viewModelScope.launch {
            appRepository.fetchPokemon(page)
                .emitBaseState(ioDispatcher)
                .collect { data ->
                    _homePokemonUiState.update { HomePokemonUiState.Success(data.data) }
                }
        }
    }

}
