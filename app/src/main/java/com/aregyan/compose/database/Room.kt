package com.aregyan.compose.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDataDao {

    // user List
    @Query("select * from UserEntity")
    fun getUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserEntity>)

    // single user
    @Query("select * from DetailsEntity WHERE user LIKE :user")
    fun getDetails(user: String): Flow<DetailsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetails(detailsEntity: DetailsEntity)
}

@Database(entities = [UserEntity::class, DetailsEntity::class], version = 1)
abstract class LocalDataSource : RoomDatabase() {
    abstract val localDataDao: LocalDataDao
}