package com.thuanpx.mvvm_architecture_compose.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.thuanpx.mvvm_architecture_compose.base.BaseViewModel
import com.thuanpx.mvvm_architecture_compose.data.remote.api.ApiService
import com.thuanpx.mvvm_architecture_compose.data.remote.datasource.PokemonDataSource
import com.thuanpx.mvvm_architecture_compose.data.repository.AppRepository
import com.thuanpx.mvvm_architecture_compose.di.AppDispatchers
import com.thuanpx.mvvm_architecture_compose.di.Dispatcher
import com.thuanpx.mvvm_architecture_compose.model.entity.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ThuanPx on 5/21/22.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService,
    private val appRepository: AppRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : BaseViewModel() {

    private val _homePokemonUiState = MutableStateFlow<HomePokemonUiState>(HomePokemonUiState.Empty)
    val homePokemonUiState: StateFlow<HomePokemonUiState> = _homePokemonUiState.asStateFlow()
    private var offset = 0
    private var pokemonList = mutableListOf<Pokemon>()

    var index = 0
    var offsetList = 0

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    val pokemonPaging = appRepository.fetchPokemon {}.cachedIn(viewModelScope)

    init {
        onLoadMore(false)
    }

    fun onLoadMore(isLoadMore: Boolean = true) {
        viewModelScope.launch {
            appRepository.fetchPokemonList(offset)
                .onStart {
                    _homePokemonUiState.update {
                        HomePokemonUiState.Loading
                    }
                }
                .catch { error ->
                    _homePokemonUiState.update {
                        HomePokemonUiState.Error(error = error)
                    }
                }
                .collect { data ->
                    offset += 20
                    pokemonList.addAll(data.data)
                    _homePokemonUiState.update {
                        HomePokemonUiState.PokemonUiState(pokemons = pokemonList)
                    }
                }
        }
    }
}

