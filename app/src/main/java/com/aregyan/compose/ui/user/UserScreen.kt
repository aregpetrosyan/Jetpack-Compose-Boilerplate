package com.aregyan.compose.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aregyan.compose.domain.UserListItem
import com.aregyan.compose.ui.theme.JetpackComposeBoilerplateTheme

@Composable
fun UserScreen() {
    val viewModel = hiltViewModel<UserViewModel>()
    val uiState = viewModel.uiState

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        items(uiState.list) { item -> 
            UserItem(item = item)
        }
    }
}

@Composable
fun UserItem(item: UserListItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.padding(16.dp).size(40.dp),
            model = item.avatar,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = item.username,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeBoilerplateTheme {
        UserScreen()
    }
}