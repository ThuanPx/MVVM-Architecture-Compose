package com.thuanpx.mvvm_architecture_compose.navigation.destination

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.thuanpx.mvvm_architecture_compose.feature.detail.DetailRoute

/**
 * Created by ThuanPx on 5/20/22.
 */

object SearchDestination: NavigationDestination {
    override val route: String = "search"
    override val destination: String = "search_destination"
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.searchGraph() {
    composable(route = SearchDestination.route) {
        DetailRoute()
    }
}