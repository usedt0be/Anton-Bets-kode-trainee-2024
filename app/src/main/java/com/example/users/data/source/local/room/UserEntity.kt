package com.example.users.data.source.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val roomId: Int? = null,

    val id: String,
    val avatarUrl: String,
    val firstName: String,
    val lastName: String,
    val userTag: String,
    val department: String,
    val position: String,
    val birthday: String,
    val phone: String
)
