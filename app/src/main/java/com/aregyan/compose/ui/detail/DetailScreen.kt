package com.aregyan.compose.ui.detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailScreen(

) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val uiState = viewModel.uiState
}