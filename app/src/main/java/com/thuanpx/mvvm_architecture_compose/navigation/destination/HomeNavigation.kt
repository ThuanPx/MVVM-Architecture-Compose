package com.thuanpx.mvvm_architecture_compose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.thuanpx.mvvm_architecture_compose.feature.home.HomeRoute

/**
 * Created by ThuanPx on 5/20/22.
 */

object HomeDestination: NavigationDestination {
    override val route: String = "home"
    override val destination: String = "home_destination"
}

fun NavGraphBuilder.homeGraph(onClickPokemonCard: (name: String) -> Unit) {
    composable(route = HomeDestination.route) {
        HomeRoute(onClick = onClickPokemonCard)
    }
}