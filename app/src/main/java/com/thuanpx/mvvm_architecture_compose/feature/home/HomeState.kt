package com.thuanpx.mvvm_architecture_compose.feature.home

import androidx.paging.PagingData
import com.thuanpx.mvvm_architecture_compose.model.entity.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * Created by ThuanPx on 23/06/2022.
 */
data class HomeState(
    val pokemonPaging: Flow<PagingData<Pokemon>> = emptyFlow()
)
