package com.aregyan.compose.ui.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aregyan.compose.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    var uiState by mutableStateOf(UserUiState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.refreshUserList()
            usersRepository.users.collect { list ->
                withContext(Dispatchers.Main) {
                    uiState = uiState.copy(list = list)
                }
            }
        }
    }

}