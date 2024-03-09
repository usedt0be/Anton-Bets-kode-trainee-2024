package com.example.users.domain.repository

import com.example.users.domain.entity.ResultsEntity
import com.example.users.domain.entity.UsersEntity

interface UsersRepository {
    suspend fun getUsers(): ResultsEntity
}