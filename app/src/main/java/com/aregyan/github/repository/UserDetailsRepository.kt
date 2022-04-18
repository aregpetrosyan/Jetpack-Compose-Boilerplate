package com.aregyan.github.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.aregyan.github.database.UsersDatabase
import com.aregyan.github.database.asDomainModel
import com.aregyan.github.domain.UserDetails
import com.aregyan.github.network.UserDetailsService
import com.aregyan.github.network.model.asDatabaseModel
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