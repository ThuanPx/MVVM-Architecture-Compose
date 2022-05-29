package com.thuanpx.mvvm_architecture_compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thuanpx.mvvm_architecture_compose.navigation.AppTopLevelNavigation
import com.thuanpx.mvvm_architecture_compose.navigation.TOP_LEVEL_DESTINATIONS
import com.thuanpx.mvvm_architecture_compose.navigation.destination.HomeDestination
import kotlinx.coroutines.CoroutineScope

/**
 * Created by ThuanPx on 5/28/22.
 */

/**
 * Remembers and creates an instance of [AppStateManager]
 */
@Composable
fun rememberAppStateManager(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppStateManager {
    return remember(navController, coroutineScope) {
        AppStateManager(navController, coroutineScope)
    }
}

/**
 * Responsible for holding state related to [MyApp] and containing UI-related logic.
 */
@Stable
class AppStateManager(
    val navController: NavHostController,
    coroutineScope: CoroutineScope
) {
    val appTopLevelNavigation = AppTopLevelNavigation(navController)

    val bottomBarRoutes = TOP_LEVEL_DESTINATIONS.map { it.route }
    val shouldShowBottomBar: Boolean
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes
    val currentRoute: String
        get() = navController.currentDestination?.route ?: HomeDestination.route


}