package com.thuanpx.mvvm_compose.core

import com.thuanpx.mvvm_compose.core.network.NetworkError


/**
 * Created by ThuanPx on 15/10/2022.
 */
sealed interface BaseUiState {
    object Init: BaseUiState
    data class Error(val networkError: NetworkError?): BaseUiState
}