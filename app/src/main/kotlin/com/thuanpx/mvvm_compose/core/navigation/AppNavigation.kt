package com.thuanpx.mvvm_compose.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thuanpx.mvvm_compose.feature.home.HomeRoute
import com.thuanpx.mvvm_compose.feature.splash.SplashRoute

/**
 * Created by ThuanPx on 01/03/2023.
 */

@Composable
fun AppNavigation(
  navController: NavHostController,
  modifier: Modifier = Modifier
) {
  NavHost(
    navController = navController,
    startDestination = RootScreen.Splash.route,
    modifier = modifier
  ) {
    composable(RootScreen.Splash.route) {
      SplashRoute {

      }
    }
    composable(RootScreen.Home.route) {
      HomeRoute()
    }
  }
}
