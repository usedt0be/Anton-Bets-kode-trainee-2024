package com.example.users.data.repository


import com.example.users.data.dto.ResultsDto
import com.example.users.data.dto.UsersDto
import com.example.users.data.source.remote.RetrofitInstance
import com.example.users.data.source.remote.UsersApi
import com.example.users.domain.entity.UsersEntity
import com.example.users.domain.repository.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(): UsersRepository {
    override suspend fun getUsers(): ResultsDto {
       return RetrofitInstance.usersApi.getUsers()
    }

}