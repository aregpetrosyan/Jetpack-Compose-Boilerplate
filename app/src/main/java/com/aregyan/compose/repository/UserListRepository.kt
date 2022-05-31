package com.aregyan.compose.repository

import com.aregyan.compose.database.UsersDatabase
import com.aregyan.compose.database.asDomainModel
import com.aregyan.compose.domain.UserListItem
import com.aregyan.compose.network.UserListService
import com.aregyan.compose.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val userListService: UserListService,
    private val database: UsersDatabase
) {

    val users: Flow<List<UserListItem>> =
        database.usersDao.getDatabaseUsers().map { it.asDomainModel() }

    suspend fun refreshUserList() {
        try {
            val users = userListService.getUserList()
            database.usersDao.insertAll(users.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}