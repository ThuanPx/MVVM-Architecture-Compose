package com.thuanpx.mvvm_architecture_compose.navigation.destination

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.thuanpx.mvvm_architecture_compose.feature.detail.DetailRoute

/**
 * Created by ThuanPx on 5/28/22.
 */

object DetailDestination : NavigationDestination {
    override val route: String
        get() = "detail"
    override val destination: String
        get() = "detail_destination"
    const val name = "name"
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.detailGraph(onClickBack: () -> Unit) {
    composable(
        route = "${DetailDestination.route}/{${DetailDestination.name}}",
        arguments = listOf(
            navArgument(DetailDestination.name) {
                type = NavType.StringType
            }
        )
    ) {
        DetailRoute(onClickBack = onClickBack)
    }
}