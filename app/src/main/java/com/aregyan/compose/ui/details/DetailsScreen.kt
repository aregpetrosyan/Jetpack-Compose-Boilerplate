package com.aregyan.compose.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aregyan.compose.ui.components.NoNetwork

@Composable
fun DetailsScreen() {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val uiState = viewModel.uiState

    if (uiState.offline) {
        NoNetwork()
    } else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = uiState.detail.avatar,
                contentDescription = null
            )
            Column {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = uiState.detail.name.orEmpty(),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = uiState.formattedUserSince,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = uiState.detail.location.orEmpty(),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}