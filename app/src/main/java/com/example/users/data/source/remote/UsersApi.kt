package com.example.users.data.source.remote

import com.example.users.data.dto.ResultsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET
    suspend fun getUsers(
        @Query("items") count:Int = 30
    ): ResultsDto

    companion object {
        const val BASE_URL = "https://stoplight.io/mocks/kode-api/trainee-test/331141861\n" +
                "/users"
    }
}