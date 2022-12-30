package com.thuanpx.mvvm_compose.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavHost(
  navHostController: NavHostController,
  composeNavigator: AppComposeNavigator
) {
  NavHost(
    navController = navHostController,
    startDestination = AppScreens.Splash.route
  ) {
    appNavigation(
      composeNavigator = composeNavigator
    )
  }
}
