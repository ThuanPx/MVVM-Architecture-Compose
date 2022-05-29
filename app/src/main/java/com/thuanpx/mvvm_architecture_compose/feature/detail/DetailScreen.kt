package com.thuanpx.mvvm_architecture_compose.feature.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.thuanpx.mvvm_architecture_compose.base.ui.component.AppGradientBackground

/**
 * Created by ThuanPx on 5/20/22.
 */

@ExperimentalMaterial3Api
@Composable
fun DetailRoute() {
    DetailScreen()
}

@ExperimentalMaterial3Api
@Composable
fun DetailScreen() {
    AppGradientBackground {
    }
}