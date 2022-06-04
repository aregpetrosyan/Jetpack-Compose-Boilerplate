package com.aregyan.compose.ui.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aregyan.compose.domain.UserListItem
import com.aregyan.compose.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class UserUiState(
    val list: List<UserListItem> = listOf()
)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userListRepository: UserListRepository
) : ViewModel() {

    var uiState by mutableStateOf(UserUiState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            userListRepository.refreshUserList()
            userListRepository.users.collect { list ->
                withContext(Dispatchers.Main) {
                    uiState = UserUiState(
                        list = list
                    )
                }
            }
        }
    }

}