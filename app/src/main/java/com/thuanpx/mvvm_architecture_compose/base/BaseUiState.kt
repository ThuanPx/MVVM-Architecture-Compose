package com.thuanpx.mvvm_architecture_compose.base

/**
 * Created by ThuanPx on 5/25/22.
 */
sealed interface BaseUiState {
    object Completed: BaseUiState
    object Loading : BaseUiState
    data class Error(val error: Throwable) : BaseUiState
}