package com.aregyan.compose.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DetailScreen() {
    val viewModel = hiltViewModel<DetailViewModel>()
    val uiState = viewModel.uiState

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
                text = formatDate(uiState.detail.userSince),
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

private fun formatDate(dateUTC: String?) : String {
    if (dateUTC.isNullOrEmpty()) return ""

    val date = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(dateUTC)
    val newFormat = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
    return date?.let { newFormat.format(it) }.orEmpty()
}