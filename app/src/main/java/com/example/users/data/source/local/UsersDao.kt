package com.example.users.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface UsersDao {

    @Query("SELECT * FROM Users")
    fun getAllUsers(): Flow<List<UsersEntity>>

    @Upsert
    suspend fun upsertAll (users: List<UsersEntity>)
    @Query("DELETE FROM Users")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM Users")
    suspend fun getRowCount(): Int



}