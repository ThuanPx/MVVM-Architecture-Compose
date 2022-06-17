package com.thuanpx.mvvm_architecture_compose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thuanpx.mvvm_architecture_compose.base.BaseUiState
import com.thuanpx.mvvm_architecture_compose.data.remote.api.ApiService
import com.thuanpx.mvvm_architecture_compose.data.remote.datasource.PokemonDataSource
import com.thuanpx.mvvm_architecture_compose.di.AppDispatchers
import com.thuanpx.mvvm_architecture_compose.di.Dispatcher
import com.thuanpx.mvvm_architecture_compose.model.entity.Pokemon
import com.thuanpx.mvvm_architecture_compose.model.entity.PokemonInfo
import com.thuanpx.mvvm_architecture_compose.utils.coroutines.dataOrException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by ThuanPx on 17/09/2021.
 */

interface AppRepository {
    fun fetchPokemon(onStateLoad: (BaseUiState) -> Unit): Flow<PagingData<Pokemon>>
    fun fetchPokemonInfo(query: String): Flow<PokemonInfo>
}

class DefaultAppRepository @Inject constructor(
    private val apiService: ApiService,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : AppRepository {

    override fun fetchPokemon(onStateLoad: (BaseUiState) -> Unit): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = { PokemonDataSource(apiService, onStateLoad) }
        ).flow
    }

    override fun fetchPokemonInfo(query: String): Flow<PokemonInfo> {
        return flow { emit(apiService.fetchPokemon(query).dataOrException()) }
    }
}
