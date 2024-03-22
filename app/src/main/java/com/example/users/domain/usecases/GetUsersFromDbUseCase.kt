package com.example.users.domain.usecases

import com.example.users.data.repository.UsersRepositoryImpl
import com.example.users.data.source.local.room.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersFromDbUseCase @Inject constructor(
    private val repository: UsersRepositoryImpl
){
    suspend fun execute(): Flow<List<UserEntity>> {
        return repository.getUsersFromDb()
    }
}