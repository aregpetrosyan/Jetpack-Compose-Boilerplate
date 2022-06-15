package com.aregyan.compose.ui.detail

import com.aregyan.compose.domain.UserDetails
import com.aregyan.compose.util.formatDate

data class DetailUiState(
    val detail: UserDetails = UserDetails()
) {
    val formattedUserSince = formatDate(detail.userSince)
}