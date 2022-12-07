package com.aglowid.myapplication.di

import android.content.Context
import androidx.room.Room
import com.aglowid.myapplication.database.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): UsersDatabase {
        return Room.databaseBuilder(
            appContext,
            UsersDatabase::class.java,
            "User"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUsersDao(usersDatabase: UsersDatabase) = usersDatabase.usersDao
}