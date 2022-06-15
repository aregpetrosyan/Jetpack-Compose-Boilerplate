package com.aregyan.compose.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    // user List
    @Query("select * from UserEntity")
    fun getDatabaseUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserEntity>)

    // single user
    @Query("select * from DetailsEntity WHERE user LIKE :user")
    fun getUserDetails(user: String): Flow<DetailsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetails(detailsEntity: DetailsEntity)
}

@Database(entities = [UserEntity::class, DetailsEntity::class], version = 1)
abstract class LocalDataSource : RoomDatabase() {
    abstract val usersDao: UsersDao
}