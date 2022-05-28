package com.aregyan.compose.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.aregyan.compose.database.UsersDatabase
import com.aregyan.compose.database.asDomainModel
import com.aregyan.compose.domain.UserDetails
import com.aregyan.compose.network.UserDetailsService
import com.aregyan.compose.network.model.asDatabaseModel
import timber.log.Timber
import javax.inject.Inject

class UserDetailsRepository @Inject constructor(
    private val userDetailsService: UserDetailsService,
    private val database: UsersDatabase
) {

    fun getUserDetails(user: String): LiveData<UserDetails> {
        return Transformations.map(database.usersDao.getUserDetails(user)) {
            it?.asDomainModel()
        }
    }


    suspend fun refreshUserDetails(user: String) {
        try {
            val userDetails = userDetailsService.getUserDetails(user)
            database.usersDao.insertUserDetails(userDetails.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }

}