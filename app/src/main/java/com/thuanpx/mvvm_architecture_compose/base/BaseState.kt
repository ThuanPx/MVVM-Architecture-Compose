package com.thuanpx.mvvm_architecture_compose.base

/**
 * Created by ThuanPx on 5/25/22.
 */
sealed interface BaseState<out T> {
    object Loading : BaseState<Nothing>
    object Empty : BaseState<Nothing>
    object Init : BaseState<Nothing>
    data class Data<T>(val value: T) : BaseState<T>
    data class Error(val throwable: Throwable) : BaseState<Nothing>
}