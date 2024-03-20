package com.example.users.domain.usecases

import com.example.users.data.repository.UsersRepositoryImpl
import com.example.users.data.source.local.room.UserEntity
import com.example.users.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RefreshUsersUseCase @Inject
constructor(private val repository: UsersRepository) {
    suspend fun execute(): Flow<List<UserEntity>> {
        return repository.refreshUsers()
    }
}