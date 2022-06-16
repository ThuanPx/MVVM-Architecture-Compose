package com.thuanpx.mvvm_architecture_compose.data.repository

import com.thuanpx.mvvm_architecture_compose.data.remote.api.ApiService
import com.thuanpx.mvvm_architecture_compose.di.AppDispatchers
import com.thuanpx.mvvm_architecture_compose.di.Dispatcher
import com.thuanpx.mvvm_architecture_compose.model.entity.Pokemon
import com.thuanpx.mvvm_architecture_compose.model.entity.PokemonInfo
import com.thuanpx.mvvm_architecture_compose.model.response.BaseResponse
import com.thuanpx.mvvm_architecture_compose.utils.coroutines.dataOrException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by ThuanPx on 17/09/2021.
 */

interface AppRepository {
    fun fetchPokemon(page: Int): Flow<BaseResponse<List<Pokemon>>>
    fun fetchPokemonInfo(query: String): Flow<PokemonInfo>
}

class DefaultAppRepository @Inject constructor(
    private val apiService: ApiService,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : AppRepository {

    override fun fetchPokemon(page: Int): Flow<BaseResponse<List<Pokemon>>> {
        return flow { emit(apiService.fetchPokemons(offset = page * 20).dataOrException()) }
            .flowOn(ioDispatcher)
    }

    override fun fetchPokemonInfo(query: String): Flow<PokemonInfo> {
        return flow { emit(apiService.fetchPokemon(query).dataOrException()) }
    }
}
