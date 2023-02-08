package com.aregyan.compose.ui.users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aregyan.compose.domain.User
import com.aregyan.compose.ui.components.NoNetwork

@Composable
fun UsersScreen(
    onUserClick: (String) -> Unit
) {
    val viewModel = hiltViewModel<UsersViewModel>()
    val uiState = viewModel.uiState

    if (uiState.offline) {
        NoNetwork()
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(uiState.list) { item ->
                UserItem(item = item, onUserClick = onUserClick)
            }
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
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}