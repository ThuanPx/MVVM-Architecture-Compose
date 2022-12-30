package com.thuanpx.mvvm_compose.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by ThuanPx on 15/10/2022.
 */
abstract class BaseViewModel : ViewModel() {
    private val _baseUiState: MutableStateFlow<BaseUiState> = MutableStateFlow(BaseUiState.Init)
    val baseUiState: StateFlow<BaseUiState> = _baseUiState.asStateFlow()

    protected fun setState(state: BaseUiState) {
        viewModelScope.launch {
            _baseUiState.emit(state)
        }
    }
}