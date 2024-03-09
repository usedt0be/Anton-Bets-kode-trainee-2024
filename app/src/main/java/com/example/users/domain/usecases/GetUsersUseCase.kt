package com.example.users.domain.usecases

import com.example.users.domain.entity.ResultsEntity
import com.example.users.domain.repository.UsersRepository

class GetUsersUseCase constructor(private val repository: UsersRepository){
    suspend fun execute(): ResultsEntity {
        return repository.getUsers()
    }
}