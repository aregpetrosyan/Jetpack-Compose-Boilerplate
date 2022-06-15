package com.aregyan.compose.repository

import com.aregyan.compose.database.LocalDataSource
import com.aregyan.compose.database.asDomainModel
import com.aregyan.compose.domain.User
import com.aregyan.compose.network.UsersApi
import com.aregyan.compose.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersApi: UsersApi,
    private val localDataSource: LocalDataSource
) {

    val users: Flow<List<User>> =
        localDataSource.localDataDao.getUsers().map { it.asDomainModel() }

    suspend fun refreshUsers() {
        try {
            val users = usersApi.getUsers()
            localDataSource.localDataDao.insertUsers(users.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}