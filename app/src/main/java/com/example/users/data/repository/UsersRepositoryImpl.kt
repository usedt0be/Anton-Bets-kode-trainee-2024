package com.example.users.data.repository


import com.example.users.data.source.remote.UsersApi
import com.example.users.domain.entity.ResultsEntity
import com.example.users.domain.repository.UsersRepository

class UsersRepositoryImpl(
    private val usersApi: UsersApi
): UsersRepository {
    override suspend fun getUsers(): ResultsEntity {
       return usersApi.getUsers()
    }

}