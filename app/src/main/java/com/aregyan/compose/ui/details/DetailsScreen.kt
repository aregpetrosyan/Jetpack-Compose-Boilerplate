package com.aregyan.compose.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aregyan.compose.R

@Composable
fun DetailsScreen() {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val uiState = viewModel.uiState

    if (uiState.offline) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 16.dp),
                    painter = painterResource(R.drawable.ic_offline),
                    tint = MaterialTheme.colors.primary,
                    contentDescription = null
                )
                Text(text = stringResource(id = R.string.please_check_network_connection))
            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
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
                    color = MaterialTheme.colors.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = uiState.formattedUserSince,
                    color = MaterialTheme.colors.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = uiState.detail.location.orEmpty(),
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}