package com.aregyan.compose.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aregyan.compose.ui.theme.JetpackComposeBoilerplateTheme

@Composable
fun UserScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeBoilerplateTheme {
        UserScreen()
    }
}