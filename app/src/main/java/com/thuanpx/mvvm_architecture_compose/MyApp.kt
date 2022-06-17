package com.thuanpx.mvvm_architecture_compose

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thuanpx.mvvm_architecture_compose.base.ui.component.AppBackground
import com.thuanpx.mvvm_architecture_compose.base.ui.theme.AppTheme
import com.thuanpx.mvvm_architecture_compose.navigation.AppNavHost
import com.thuanpx.mvvm_architecture_compose.navigation.TOP_LEVEL_DESTINATIONS
import com.thuanpx.mvvm_architecture_compose.navigation.TopLevelDestination

/**
 * Created by ThuanPx on 5/20/22.
 */

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
fun MyApp() {
    val appStateManager = rememberAppStateManager()

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            if (appStateManager.shouldShowBottomBar) {
                AppBottomBar(
                    navigateToRoute = appStateManager.appTopLevelNavigation::navigateTo,
                    currentRoute = appStateManager.currentRoute
                )
            }
        }
    ) {
        AppNavHost(
            navController = appStateManager.navController,
            modifier = Modifier
                .padding(it)
                .consumedWindowInsets(it)
        )
    }
}

@Composable
private fun AppBottomBar(
    navigateToRoute: (TopLevelDestination) -> Unit,
    currentRoute: String
) {
    // Wrap the navigation bar in a surface so the color behind the system
    // navigation is equal to the container color of the navigation bar.
    Surface(color = MaterialTheme.colorScheme.surface) {
        NavigationBar(
            modifier = Modifier.windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                )
            ),
            tonalElevation = 0.dp
        ) {
            TOP_LEVEL_DESTINATIONS.forEach { destination ->
                val selected = destination.route == currentRoute
                NavigationBarItem(
                    selected = selected,
                    onClick = { navigateToRoute(destination) },
                    icon = {
                        Icon(
                            if (selected) {
                                destination.selectedIcon
                            } else {
                                destination.unselectedIcon
                            },
                            contentDescription = null
                        )
                    },
                    label = { Text(stringResource(destination.iconTextId)) }
                )
            }
        }
    }
}