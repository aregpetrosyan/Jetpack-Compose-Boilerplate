package com.aregyan.compose.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    // user List
    @Query("select * from DatabaseUserListItem")
    fun getDatabaseUsers(): Flow<List<DatabaseUserListItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<DatabaseUserListItem>)

    // single user
    @Query("select * from DatabaseUserDetails WHERE user LIKE :user")
    fun getUserDetails(user: String): Flow<DatabaseUserDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetails(databaseUserDetails: DatabaseUserDetails)
}

@Database(entities = [DatabaseUserListItem::class, DatabaseUserDetails::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao
}