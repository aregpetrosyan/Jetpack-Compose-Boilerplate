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
        startDestination = Screen.User.route
    ) {
        composable(Screen.User.route) {
            UserScreen(
                onUserClick = { username ->
                    navController.navigate("${Screen.Detail.route}/$username")
                }
            )
        }
        composable(
            route = "${Screen.Detail.route}/{${Argument.Username.value}}",
            arguments = listOf(
                navArgument(Argument.Username.value) {
                    type = NavType.StringType
                }
            ),
        ) {
            DetailScreen()
        }
    }
}

enum class Screen(val route: String) {
    User("user"),
    Detail("detail")
}

enum class Argument(val value: String) {
    Username("username")
}