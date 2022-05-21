package com.thuanpx.mvvm_architecture_compose.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thuanpx.mvvm_architecture_compose.base.ui.component.AppGradientBackground

/**
 * Created by ThuanPx on 5/20/22.
 */

@ExperimentalMaterial3Api
@Composable
fun DetailRoute(modifier: Modifier = Modifier) {
    DetailScreen(modifier)
}

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(modifier: Modifier = Modifier) {
    AppGradientBackground {
    }
}