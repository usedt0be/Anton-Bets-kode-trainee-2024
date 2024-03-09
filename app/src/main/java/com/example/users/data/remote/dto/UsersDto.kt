package com.example.users.data.remote.dto

import com.example.users.domain.entity.UsersEntity

data class UsersDto(

    override val id: String,
    override val avatarUrl: String,
    override val firstName: String,
    override val lastName: String,
    override val userTag: String,
    override val department: String,
    override val position: String,
    override val birthday: String,
    override val phone: String
):UsersEntity {

}
