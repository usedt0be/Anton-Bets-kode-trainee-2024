package com.example.users.presentation.di

import com.example.users.data.repository.UsersRepositoryImpl
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
    fun provideUsersRepository(): UsersRepositoryImpl {
        return UsersRepositoryImpl()
    }


}