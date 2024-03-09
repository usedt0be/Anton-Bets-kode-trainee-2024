package com.example.users.data.source.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(UsersApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val usersApi: UsersApi = retrofit.create(UsersApi::class.java)
    }

}