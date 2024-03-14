package com.example.users.data.mappers

import com.example.users.data.dto.UserDto
import com.example.users.data.source.local.UserEntity
import com.example.users.presentation.User

fun UserDto.toUsersEntity(): UserEntity {
    return UserEntity(
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



fun UserEntity.toUsers(): User {
    return User(
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