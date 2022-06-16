package com.thuanpx.mvvm_architecture_compose.base.ui.component

import androidx.compose.runtime.Composable
import com.thuanpx.mvvm_architecture_compose.base.BaseUiState

/**
 * Created by ThuanPx on 6/15/22.
 */

@Composable
fun BaseUiState.HandleBaseState() {
    when(this) {
        is BaseUiState.Loading -> LoadingWheel()
        is BaseUiState.Error ->  AppErrorAlertDialog(text = this.error.toString())
        else -> {}
    }
}