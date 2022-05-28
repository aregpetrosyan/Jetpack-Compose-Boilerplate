package com.aregyan.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aregyan.compose.ui.theme.JetpackComposeBoilerplateTheme
import com.aregyan.compose.ui.user.UserScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBoilerplateTheme {
                // A surface container using the 'background' color from the theme
                UserScreen()
            }
        }
    }
}