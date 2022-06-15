package com.aregyan.compose.ui.detail

import com.aregyan.compose.domain.Details
import com.aregyan.compose.util.formatDate

data class DetailUiState(
    val detail: Details = Details()
) {
    val formattedUserSince = formatDate(detail.userSince)
}