package com.thuanpx.mvvm_architecture_compose.feature.detail

import androidx.lifecycle.SavedStateHandle
import com.thuanpx.mvvm_architecture_compose.base.BaseViewModel
import com.thuanpx.mvvm_architecture_compose.base.BaseState
import com.thuanpx.mvvm_architecture_compose.data.repository.AppRepository
import com.thuanpx.mvvm_architecture_compose.di.AppDispatchers
import com.thuanpx.mvvm_architecture_compose.di.Dispatcher
import com.thuanpx.mvvm_architecture_compose.navigation.destination.DetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by ThuanPx on 6/15/22.
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val appRepository: AppRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<BaseState<DetailState>>() {

    private val name: String = checkNotNull(savedStateHandle[DetailDestination.name])

    init {
        fetchDetailPokemon()
    }

    private fun fetchDetailPokemon() {
        viewModelScope(
            callFlow = appRepository.fetchPokemonInfo(name),
            flowOn = ioDispatcher,
            collect = {
                setState(BaseState.Data(DetailState(it)))
            }
        )
    }
}
