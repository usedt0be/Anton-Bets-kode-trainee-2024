package com.example.users.data.dto

import com.example.users.domain.entity.UsersEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersDto(
    @Json(name= "id")
    override val id: String,
    @Json(name= "avatarUrl")
    override val avatarUrl: String,
    @Json(name= "firstName")
    override val firstName: String,
    @Json(name= "lastName")
    override val lastName: String,
    @Json(name= "userTag")
    override val userTag: String,
    @Json(name= "department")
    override val department: String,
    @Json(name= "position")
    override val position: String,
    @Json(name= "birthday")
    override val birthday: String,
    @Json(name= "phone")
    override val phone: String
):UsersEntity
