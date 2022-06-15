package com.aregyan.compose.ui.user

import com.aregyan.compose.domain.User

data class UserUiState(
    val list: List<User> = listOf()
)