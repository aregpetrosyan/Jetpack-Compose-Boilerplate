package com.aregyan.compose.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aregyan.compose.domain.UserListItem
import com.aregyan.compose.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userListRepository: UserListRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState.Success(emptyList()))
    val uiState: StateFlow<UserUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            userListRepository.refreshUserList()
            userListRepository.users.collect { list ->
                _uiState.value = UserUiState.Success(list)
            }
        }
    }

}

sealed class UserUiState {
    data class Success(val users: List<UserListItem>): UserUiState()
    data class Error(val exception: Throwable): UserUiState()
}