package com.aregyan.compose.repository

import com.aregyan.compose.database.UsersDatabase
import com.aregyan.compose.database.asDomainModel
import com.aregyan.compose.domain.UserDetails
import com.aregyan.compose.network.UserDetailsService
import com.aregyan.compose.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UserDetailsRepository @Inject constructor(
    private val userDetailsService: UserDetailsService,
    private val database: UsersDatabase
) {

    fun getUserDetails(user: String): Flow<UserDetails> =
        database.usersDao.getUserDetails(user).map { it.asDomainModel() }

    suspend fun refreshUserDetails(user: String) {
        try {
            val userDetails = userDetailsService.getUserDetails(user)
            database.usersDao.insertUserDetails(userDetails.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }

}