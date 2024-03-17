package com.example.users.data.source.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "Users_fts")
@Fts4(contentEntity = UserEntity::class)
data class UserFTS(
    @ColumnInfo(name = "rowid")
    @PrimaryKey
    val roomId : Int,

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
