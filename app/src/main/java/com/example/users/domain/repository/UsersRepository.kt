package com.example.users.domain.repository

import com.example.users.data.source.local.UsersEntity
import com.example.users.domain.entity.ResultsEntity
import kotlinx.coroutines.flow.Flow


interface UsersRepository {

    suspend fun getUsersFromDb(): Flow<List<UsersEntity>>
    suspend fun isDatabaseEmpty(): Boolean
}