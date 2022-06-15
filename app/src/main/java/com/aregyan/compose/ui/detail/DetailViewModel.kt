package com.aregyan.compose.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aregyan.compose.repository.DetailsRepository
import com.aregyan.compose.ui.Argument
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val username: String? = savedStateHandle[Argument.USERNAME]
    var uiState by mutableStateOf(DetailUiState())
        private set

    init {
        username?.let {
            viewModelScope.launch(Dispatchers.IO) {
                detailsRepository.refreshUserDetails(it)
                detailsRepository.getUserDetails(it).collect { detail ->
                    withContext(Dispatchers.Main) {
                        uiState = uiState.copy(detail = detail)
                    }
                }
            }
        }
    }

}