package com.thuanpx.mvvm_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thuanpx.mvvm_compose.core.navigation.AppNavigation
import com.thuanpx.mvvm_compose.designsystem.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      DesignSystemCatalogApp()
    }
  }
}

@Composable
fun MyApp() {
  AppTheme {
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
      systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
      )
      onDispose {}
    }
    val navController = rememberNavController()
    Scaffold { padding ->
      AppNavigation(
        navController = navController,
        modifier = Modifier.padding(padding)
      )
    }
  }
}
