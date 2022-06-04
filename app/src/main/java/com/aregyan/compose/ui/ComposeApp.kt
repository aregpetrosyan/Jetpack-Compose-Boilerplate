package com.aregyan.compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aregyan.compose.ui.user.UserScreen

@Composable
fun ComposeApp() {
    NavHost(
        navController = rememberNavController(),
        startDestination = Screen.User.route
    ) {
        composable(Screen.User.route) {
            UserScreen(
                onUserClick = {
                              
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object User : Screen("user")
}