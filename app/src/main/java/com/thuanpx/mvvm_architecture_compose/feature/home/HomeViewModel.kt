package com.thuanpx.mvvm_architecture_compose.feature.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.thuanpx.mvvm_architecture_compose.base.BaseViewModel
import com.thuanpx.mvvm_architecture_compose.base.BaseState
import com.thuanpx.mvvm_architecture_compose.data.repository.AppRepository
import com.thuanpx.mvvm_architecture_compose.di.AppDispatchers
import com.thuanpx.mvvm_architecture_compose.di.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by ThuanPx on 5/21/22.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : BaseViewModel<BaseState<HomeState>>() {


    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() = viewModelScope {
        setState(BaseState.Loading)
        val pokemonPaging = appRepository.fetchPokemon().cachedIn(viewModelScope)
        setState(BaseState.Data(HomeState(pokemonPaging = pokemonPaging)))
    }
}

