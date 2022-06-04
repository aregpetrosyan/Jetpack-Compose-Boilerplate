package com.aregyan.compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aregyan.compose.ui.detail.DetailScreen
import com.aregyan.compose.ui.user.UserScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.USER
    ) {
        composable(Route.USER) {
            UserScreen(
                onUserClick = { username ->
                    navController.navigate("${Route.DETAIL}/$username")
                }
            )
        }
        composable(
            route = "${Route.DETAIL}/{${Argument.USERNAME}}",
            arguments = listOf(
                navArgument(Argument.USERNAME) {
                    type = NavType.StringType
                }
            ),
        ) {
            DetailScreen()
        }
    }
}

object Route {
    const val USER = "user"
    const val DETAIL = "detail"
}

object Argument {
    const val USERNAME = "username"
}