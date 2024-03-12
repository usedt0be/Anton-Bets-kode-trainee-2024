package com.example.users.data.mappers

import com.example.users.data.dto.UsersDto
import com.example.users.data.source.local.UsersEntity
import com.example.users.presentation.Users

fun UsersDto.toUsersEntity(): UsersEntity {
    return UsersEntity(
        id = id,
        avatarUrl = avatarUrl,
        firstName = firstName,
        lastName = lastName,
        userTag = userTag,
        department = department,
        position = position,
        birthday = birthday,
        phone = phone
    )
}



fun UsersEntity.toUsers(): Users {
    return Users(
        id = id,
        avatarUrl = avatarUrl,
        firstName = firstName,
        lastName = lastName,
        userTag = userTag,
        department = department,
        position = position,
        birthday = birthday,
        phone = phone
    )
}