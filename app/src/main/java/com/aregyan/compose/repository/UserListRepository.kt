package com.aregyan.compose.repository

import com.aregyan.compose.database.LocalDataSource
import com.aregyan.compose.database.asDomainModel
import com.aregyan.compose.domain.UserListItem
import com.aregyan.compose.network.UsersRemoteDataSource
import com.aregyan.compose.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    val users: Flow<List<UserListItem>> =
        localDataSource.usersDao.getDatabaseUsers().map { it.asDomainModel() }

    suspend fun refreshUserList() {
        try {
            val users = usersRemoteDataSource.getUsers()
            localDataSource.usersDao.insertAll(users.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}