package com.aregyan.compose.ui.details

import com.aregyan.compose.domain.Details
import com.aregyan.compose.util.formatDate

data class DetailsUiState(
    val detail: Details = Details()
) {
    val formattedUserSince = formatDate(detail.userSince)
}