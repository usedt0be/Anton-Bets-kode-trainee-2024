package com.example.users.domain.repository

import com.example.users.data.source.local.UserEntity
import kotlinx.coroutines.flow.Flow


interface UsersRepository {

    suspend fun getUsersFromDb(): Flow<List<UserEntity>>
    suspend fun isDatabaseEmpty(): Boolean
}