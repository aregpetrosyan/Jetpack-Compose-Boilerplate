package com.aregyan.compose.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aregyan.compose.domain.User

@Composable
fun UserScreen(
    onUserClick: (String) -> Unit
) {
    val viewModel = hiltViewModel<UserViewModel>()
    val uiState = viewModel.uiState

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        items(uiState.list) { item ->
            UserItem(item = item, onUserClick = onUserClick)
        }
    }
}

@Composable
fun UserItem(item: User, onUserClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onUserClick(item.username) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(40.dp),
            model = item.avatar,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = item.username,
            color = MaterialTheme.colors.onBackground
        )
    }
}