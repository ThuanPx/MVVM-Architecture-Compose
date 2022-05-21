package com.thuanpx.mvvm_architecture_compose.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

/**
 * Created by ThuanPx on 5/25/22.
 */
open class BaseViewModel : ViewModel() {
    protected val _baseUiState = MutableStateFlow<BaseUiState>(BaseUiState.Loading)
    val baseUiState: StateFlow<BaseUiState> = _baseUiState.asStateFlow()

    fun emitLoading() {
        _baseUiState.update { BaseUiState.Loading }
    }

    fun emitError(error: Throwable) {
        _baseUiState.update { BaseUiState.Error(error) }
    }

    fun emitCompleted() {
        _baseUiState.update { BaseUiState.Completed }
    }

    protected fun <T> Flow<T>.emitBaseState(dispatcher: CoroutineDispatcher): Flow<T> {
        return onStart { emitLoading() }
            .onCompletion { emitCompleted() }
            .catch { emitError(it) }
            .flowOn(dispatcher)
    }

}
