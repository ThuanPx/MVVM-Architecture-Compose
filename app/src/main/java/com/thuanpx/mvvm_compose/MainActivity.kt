package com.thuanpx.mvvm_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.thuanpx.mvvm_compose.core.navigation.AppComposeNavigator
import com.thuanpx.mvvm_compose.core.navigation.AppNavHost
import com.thuanpx.mvvm_compose.designsystem.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


  @Inject
  lateinit var appComposeNavigation: AppComposeNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      App(appComposeNavigation)
    }
  }
}

@Composable
fun App(
  composeNavigator: AppComposeNavigator
) {
  val navHostController = rememberNavController()
  LaunchedEffect(key1 = Unit, block = {
    composeNavigator.handleNavigationCommands(navHostController)
  })
  AppTheme {
    AppNavHost(
      navHostController = navHostController,
      composeNavigator = composeNavigator
    )
  }
}


//@Composable
//private fun AppBottomBar(
//    destinations: List<TopLevelDestination>,
//    onNavigateToDestination: (TopLevelDestination) -> Unit,
//    currentDestination: NavDestination?
//) {
//    Surface() {
//        AppNavigationBar(
//            modifier = Modifier
//                .height(75.dp)
//                .background(color = Color.White)
//        ) {
//            destinations.forEach { destination ->
//                val selected =
//                    currentDestination?.hierarchy?.any { it.route == destination.route } == true
//                AppNavigationBarItem(
//                    selected = selected,
//                    onClick = { onNavigateToDestination(destination) },
//                    icon = {
//                        Icon(
//                            painter = painterResource(id = destination.unselectedIcon),
//                            contentDescription = null,
//                            tint = Gray700
//                        )
//                    },
//                    selectedIcon = {
//                        Icon(
//                            painter = painterResource(id = destination.selectedIcon),
//                            contentDescription = null,
//                            tint = primary,
//                        )
//                    },
//                    label = { Text(destination.label) })
//            }
//        }
//    }
//}