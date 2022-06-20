package com.aregyan.compose.ui.users

import com.aregyan.compose.domain.User

data class UsersUiState(
    val list: List<User> = listOf(),
    val offline: Boolean = false
)