package com.example.users.data.source.remote

import com.example.users.data.dto.ResultsDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UsersApi {

    @Headers(
        "Accept: application/json",
        "Content-type: application/json",
    )
    @GET("users")
    suspend fun getUsers(
        @Query("items") items: Int = 9
    ): ResultsDto

    companion object {
        const val BASE_URL = "https://stoplight.io/mocks/kode-api/trainee-test/331141861/"
    }
}