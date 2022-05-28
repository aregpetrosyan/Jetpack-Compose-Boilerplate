package com.aregyan.compose.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aregyan.compose.domain.UserDetails
import com.aregyan.compose.repository.UserDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userDetailsRepository: UserDetailsRepository
) : ViewModel() {

//    val userDetails = ObservableParcelable(UserDetails())

    fun getUserDetails(user: String) = userDetailsRepository.getUserDetails(user)

    fun refreshUserDetails(user: String) = viewModelScope.launch(Dispatchers.IO) {
        userDetailsRepository.refreshUserDetails(user)
    }

}