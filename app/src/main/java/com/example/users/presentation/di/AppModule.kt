package com.example.users.presentation.di

import android.app.Application
import com.example.users.data.repository.UsersRepositoryImpl
import com.example.users.data.source.local.room.UsersDao
import com.example.users.data.source.local.room.UsersDatabase
import com.example.users.data.source.remote.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class AppModule {
    @Singleton
    @Provides
    fun provideUsersRepository(usersDao: UsersDao): UsersRepositoryImpl {
        return UsersRepositoryImpl(usersDao = usersDao, usersApi = RetrofitInstance.usersApi)
    }

    @Singleton
    @Provides
    fun provideUsersDao(usersDatabase: UsersDatabase): UsersDao {
        return usersDatabase.usersDao
    }


    @Singleton
    @Provides
    fun provideUsersDatabase(context: Application): UsersDatabase {
       return  UsersDatabase.getInstance(context)
    }
}