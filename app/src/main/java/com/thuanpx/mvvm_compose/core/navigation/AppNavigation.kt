package com.thuanpx.mvvm_compose.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.thuanpx.mvvm_compose.feature.home.HomeScreen
import com.thuanpx.mvvm_compose.feature.splash.LoginScreen

fun NavGraphBuilder.appNavigation(
  composeNavigator: AppComposeNavigator
) {
  composable(route = AppScreens.Splash.name) {
    LoginScreen(composeNavigator = composeNavigator)
  }

  composable(route = AppScreens.Home.name) {
    HomeScreen(appComposeNavigator = composeNavigator)
  }

}
