package com.thuanpx.mvvm_architecture_compose.feature.home

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

    val pokemonPaging = appRepository.fetchPokemon {}.cachedIn(viewModelScope)
}
