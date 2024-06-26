package com.example.users.data.repository


import android.util.Log
import com.example.users.data.mappers.toUsersEntity
import com.example.users.data.source.local.room.UsersDao
import com.example.users.data.source.local.room.UserEntity
import com.example.users.data.source.remote.UsersApi
import com.example.users.domain.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(
    private  val usersDao: UsersDao,
    private val usersApi: UsersApi
): UsersRepository {
    override suspend fun getUsersWithoutInternet(): Flow<List<UserEntity>> {
        return flow {
            emitAll(usersDao.getAllUsers())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUsersFromDb(): Flow<List<UserEntity>>  {
        val preloadedUsers = usersApi.getUsers().items
        return flow {
            if(isDatabaseEmpty()) {
                usersDao.upsertAll(preloadedUsers.map {it.toUsersEntity()})
                emitAll(usersDao.getAllUsers())
            } else {
                emitAll(usersDao.getAllUsers())
            }
        }.flowOn(Dispatchers.IO)
    }



    override suspend fun isDatabaseEmpty(): Boolean {
        val rowCount = usersDao.getRowCount()
        return rowCount == 0
    }

    override fun findUsers(query: String): Flow<List<UserEntity>> {
        return usersDao.foundUsers(query)
    }

    override suspend fun refreshUsers(): Flow<List<UserEntity>> {
        val newUsers = usersApi.getUsers().items
        usersDao.clearAll()
        usersDao.upsertAll(users = newUsers.map { it.toUsersEntity() })
        return flow{
           emitAll( usersDao.getAllUsers())
        }.flowOn(Dispatchers.IO)
    }


}