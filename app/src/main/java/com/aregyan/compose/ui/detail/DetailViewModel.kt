package com.aregyan.compose.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aregyan.compose.domain.UserDetails
import com.aregyan.compose.repository.UserDetailsRepository
import com.aregyan.compose.ui.Argument
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

data class DetailUiState(
    val detail: UserDetails = UserDetails()
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userDetailsRepository: UserDetailsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val username: String? = savedStateHandle[Argument.USERNAME]
    var uiState by mutableStateOf(DetailUiState())
        private set

    init {
        username?.let {
            viewModelScope.launch(Dispatchers.IO) {
                userDetailsRepository.refreshUserDetails(it)
                userDetailsRepository.getUserDetails(it).collect { detail ->
                    withContext(Dispatchers.Main) {
                        uiState = DetailUiState(
                            detail = detail.copy(
                                userSince = formatDate(detail.userSince)
                            )
                        )
                    }
                }
            }
        }
    }

}

private fun formatDate(dateUTC: String?) : String {
    if (dateUTC.isNullOrEmpty()) return ""

    val date = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(dateUTC)
    val newFormat = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
    return date?.let { newFormat.format(it) }.orEmpty()
}