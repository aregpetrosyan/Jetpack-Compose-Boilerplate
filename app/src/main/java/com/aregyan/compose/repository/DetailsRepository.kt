package com.aregyan.compose.repository

import com.aregyan.compose.database.AppDatabase
import com.aregyan.compose.database.asDomainModel
import com.aregyan.compose.domain.Details
import com.aregyan.compose.network.DetailsApi
import com.aregyan.compose.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val detailsApi: DetailsApi,
    private val appDatabase: AppDatabase
) {

    fun getUserDetails(user: String): Flow<Details?> =
        appDatabase.usersDao.getDetails(user).map { it?.asDomainModel() }

    suspend fun refreshDetails(user: String) {
        try {
            val userDetails = detailsApi.getDetails(user)
            appDatabase.usersDao.insertDetails(userDetails.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }

}