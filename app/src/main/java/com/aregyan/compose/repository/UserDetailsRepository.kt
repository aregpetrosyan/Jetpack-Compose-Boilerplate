package com.aregyan.compose.repository

import com.aregyan.compose.database.LocalDataSource
import com.aregyan.compose.database.asDomainModel
import com.aregyan.compose.domain.UserDetails
import com.aregyan.compose.network.DetailsRemoteDataSource
import com.aregyan.compose.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UserDetailsRepository @Inject constructor(
    private val detailsRemoteDataSource: DetailsRemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    fun getUserDetails(user: String): Flow<UserDetails> =
        localDataSource.usersDao.getUserDetails(user).map { it.asDomainModel() }

    suspend fun refreshUserDetails(user: String) {
        try {
            val userDetails = detailsRemoteDataSource.getDetails(user)
            localDataSource.usersDao.insertUserDetails(userDetails.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }

}