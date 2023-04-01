package com.thuanpx.mvvm_compose.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thuanpx.mvvm_compose.core.PhoneDevicePreviews
import com.thuanpx.mvvm_compose.designsystem.component.AppBackground
import com.thuanpx.mvvm_compose.designsystem.theme.AppTheme

/**
 * Created by ThuanPx on 02/01/2023.
 */

@Composable
fun HomeRoute() {
  HomeScreen()
}

@Composable
fun HomeScreen() {
  AppBackground {
    Box(modifier = Modifier.fillMaxSize()) {
      Scaffold(modifier = Modifier.fillMaxSize()) { padding ->

      }
    }
  }
}

@PhoneDevicePreviews
@Composable
fun PreviewHomeScreen() {
  AppTheme {
    HomeScreen()
  }
}