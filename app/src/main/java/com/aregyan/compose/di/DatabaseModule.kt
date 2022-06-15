package com.aregyan.compose.di

import android.content.Context
import androidx.room.Room
import com.aregyan.compose.database.LocalDataDao
import com.aregyan.compose.database.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): LocalDataSource {
        return Room.databaseBuilder(
            appContext,
            LocalDataSource::class.java,
            "Users"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(localDataSource: LocalDataSource): LocalDataDao {
        return localDataSource.localDataDao
    }

}