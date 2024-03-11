package com.example.users.domain.usecases

import com.example.users.data.dto.ResultsDto
import com.example.users.data.dto.UsersDto
import com.example.users.data.repository.UsersRepositoryImpl
import com.example.users.domain.entity.ResultsEntity
import com.example.users.domain.entity.UsersEntity
import com.example.users.domain.repository.UsersRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UsersRepositoryImpl
){
    suspend fun execute(): List<UsersEntity> {
        return repository.getUsers().results
    }
}