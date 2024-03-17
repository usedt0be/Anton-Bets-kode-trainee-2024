package com.example.users.domain.repository

import androidx.room.Query
import com.example.users.data.source.local.room.UserEntity
import kotlinx.coroutines.flow.Flow


interface UsersRepository {

    suspend fun getUsersFromDb(): Flow<List<UserEntity>>
    suspend fun isDatabaseEmpty(): Boolean

    fun findUsers(query: String): Flow<List<UserEntity>>
}