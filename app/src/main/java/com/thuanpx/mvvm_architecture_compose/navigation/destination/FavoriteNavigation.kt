package com.thuanpx.mvvm_architecture_compose.navigation.destination

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.thuanpx.mvvm_architecture_compose.feature.favorite.FavoriteRoute

/**
 * Created by ThuanPx on 5/20/22.
 */

object FavoriteDestination: NavigationDestination {
    override val route: String = "favorite"
    override val destination: String = "favorite_destination"
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.favoriteGraph() {
    composable(route = FavoriteDestination.route) {
        FavoriteRoute()
    }
}