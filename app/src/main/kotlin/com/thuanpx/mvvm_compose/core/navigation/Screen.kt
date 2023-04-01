package com.thuanpx.mvvm_compose.core.navigation

/**
 * Created by ThuanPx on 01/03/2023.
 */

sealed class RootScreen(val route: String) {
  object Home : RootScreen("home")
  object Splash : RootScreen("splash")
  object Login : RootScreen("login")
}

sealed class Screen(
  val route: String
) {
  fun createRoute(root: RootScreen) = "${root.route}/$route"

  object Write : Screen("write")
  object User : Screen("user")
  object History : Screen("history")
  object HistoryDetail : Screen("history/detail")
}