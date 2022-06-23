package com.thuanpx.mvvm_architecture_compose.data.remote.datasource

import com.thuanpx.mvvm_architecture_compose.base.BaseDataSource
import com.thuanpx.mvvm_architecture_compose.base.BaseUiState
import com.thuanpx.mvvm_architecture_compose.data.remote.api.ApiService
import com.thuanpx.mvvm_architecture_compose.model.entity.Pokemon
import com.thuanpx.mvvm_architecture_compose.model.response.BaseResponse
import com.thuanpx.mvvm_architecture_compose.utils.coroutines.ApiResponse

/**
 * Created by ThuanPx on 16/06/2022.
 */
class PokemonDataSource(
    private val apiService: ApiService,
) : BaseDataSource<Pokemon>() {

    override suspend fun requestMore(nextPage: Int): ApiResponse<BaseResponse<List<Pokemon>>> {
        return apiService.fetchPokemons(offset = nextPage * 20)
    }
}