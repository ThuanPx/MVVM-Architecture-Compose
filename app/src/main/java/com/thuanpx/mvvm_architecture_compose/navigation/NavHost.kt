package com.thuanpx.mvvm_architecture_compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.thuanpx.mvvm_architecture_compose.navigation.destination.HomeDestination
import com.thuanpx.mvvm_architecture_compose.navigation.destination.SearchDestination
import com.thuanpx.mvvm_architecture_compose.navigation.destination.homeGraph
import com.thuanpx.mvvm_architecture_compose.navigation.destination.searchGraph

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph()
        searchGraph()
    }
}
