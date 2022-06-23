package com.thuanpx.mvvm_architecture_compose.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thuanpx.mvvm_architecture_compose.base.network.DataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by ThuanPx on 5/25/22.
 */
abstract class BaseViewModel<S : BaseState<*>> : ViewModel() {

    private val _uiState = MutableStateFlow<BaseState<*>>(BaseState.Init)
    val uiState = _uiState.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        handleError(exception)
    }

    open fun handleError(exception: Throwable) {
        _uiState.value = BaseState.Error(exception)
    }

    open fun startLoading() {
        _uiState.value = BaseState.Loading
    }

    protected fun setState(state: S) = viewModelScope {
        _uiState.emit(state)
    }

    protected fun viewModelScope(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(context = handler, block = block)
    }

    protected fun <T> viewModelScope(
        callFlow: Flow<DataState<T>>,
        flowOn: CoroutineDispatcher,
        collect: (collect: T) -> Unit = {}
    ): Job {
        return viewModelScope.launch(handler) {
            callFlow
                .onStart { startLoading() }
                .catch { handleError(it) }
                .flowOn(flowOn)
                .collect { state ->
                    when (state) {
                        is DataState.Error -> handleError(state.error)
                        is DataState.Success -> collect.invoke(state.result)
                    }
                }
        }
    }

}
