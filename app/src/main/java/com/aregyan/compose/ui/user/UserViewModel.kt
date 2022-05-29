package com.aregyan.compose.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aregyan.compose.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userListRepository: UserListRepository
) : ViewModel() {

//    val data = userListRepository.users

    init {
        viewModelScope.launch(Dispatchers.IO) {
            userListRepository.refreshUserList()
        }
    }

}