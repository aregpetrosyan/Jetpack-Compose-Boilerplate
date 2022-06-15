package com.aregyan.compose.ui.user

import com.aregyan.compose.domain.UserListItem

data class UserUiState(
    val list: List<UserListItem> = listOf()
)