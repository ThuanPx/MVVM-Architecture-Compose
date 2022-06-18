package com.thuanpx.mvvm_architecture_compose.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thuanpx.mvvm_architecture_compose.base.BaseUiState
import com.thuanpx.mvvm_architecture_compose.base.BaseViewModel
import com.thuanpx.mvvm_architecture_compose.data.repository.AppRepository
import com.thuanpx.mvvm_architecture_compose.di.AppDispatchers
import com.thuanpx.mvvm_architecture_compose.di.Dispatcher
import com.thuanpx.mvvm_architecture_compose.model.entity.PokemonInfo
import com.thuanpx.mvvm_architecture_compose.navigation.destination.DetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ThuanPx on 6/15/22.
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val appRepository: AppRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Empty)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

    private val name: String = checkNotNull(savedStateHandle[DetailDestination.name])

    init {
        fetchDetailPokemon()
    }

    fun fetchDetailPokemon() {
        viewModelScope.launch {
            appRepository.fetchPokemonInfo(name)
                .emitBaseState(ioDispatcher)
                .collect { pokemon ->
                    _detailUiState.update { DetailUiState.Success(pokemon) }
                }
        }
    }
}
