package com.thuanpx.mvvm_compose.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thuanpx.mvvm_compose.core.loading.LoadingCounterStateFlow
import com.thuanpx.mvvm_compose.core.loading.UiMessage
import com.thuanpx.mvvm_compose.core.loading.UiMessageManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by ThuanPx on 15/10/2022.
 */
abstract class BaseViewModel : ViewModel() {
  protected val uiMessageManager = UiMessageManager()
  protected val loadingCounter = LoadingCounterStateFlow()

  fun onClearMessenger(id: Long) {
    viewModelScope.launch {
      uiMessageManager.clearMessage(id)
    }
  }

  fun showError(throwable: Throwable) {
    viewModelScope.launch {
      uiMessageManager.emitMessage(UiMessage(throwable))
    }
  }

  fun showLoading(isLoading: Boolean) {
    viewModelScope.launch {
      if (isLoading) {
        loadingCounter.addLoader()
      } else {
        loadingCounter.removeLoader()
      }
    }
  }

  suspend inline fun <T> Flow<T>.async(
    loading: LoadingCounterStateFlow,
    uiMessageManager: UiMessageManager,
    crossinline action: suspend (T) -> Unit
  ) {
    this.onStart { loading.addLoader() }
      .onCompletion { loading.removeLoader() }
      .catch {
        uiMessageManager.emitMessage(UiMessage(it))
      }
      .collect {
        action.invoke(it)
      }
  }
}