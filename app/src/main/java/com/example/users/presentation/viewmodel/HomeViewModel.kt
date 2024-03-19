package com.example.users.presentation.viewmodel

import android.database.sqlite.SQLiteException
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.users.data.mappers.toUsers
import com.example.users.data.repository.UsersRepositoryImpl
import com.example.users.data.source.local.room.UserEntity
import com.example.users.domain.entity.UsersEntity
import com.example.users.domain.usecases.GetUsersFromDbUseCase
import com.example.users.presentation.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
       private val repositoryImpl: UsersRepositoryImpl,
    val savedStateHandle: SavedStateHandle
): ViewModel() {


    private val getUsersFromDbUseCase = GetUsersFromDbUseCase(repositoryImpl)

    private val _usersList = MutableStateFlow<List<User>>(emptyList())
    val usersList = _usersList

    init {
        getUsersFromDb()
    }

    fun dd() {
        savedStateHandle.get<Int>("userId")
    }
    private fun getUsersFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val usersFlow = getUsersFromDbUseCase.execute()
            usersFlow.collect {usersList ->
                _usersList.value = usersList.map { it.toUsers() }
            }
        }
    }

    fun findUser(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val findedUsers = if (query.isBlank()) {
                repositoryImpl.getUsersFromDb()
            } else {
                try {
                    repositoryImpl.findUsers("*$query*")
                } catch (e:SQLiteException) {
                    repositoryImpl.getUsersFromDb()
                }
            }
            updateUiWithUsers(findedUsers)
        }
    }


    fun updateUiWithUsers(findedUsers: Flow<List<UserEntity>>) {
        viewModelScope.launch(Dispatchers.IO) {
                findedUsers.collect {listOfUsers ->
                    _usersList.value = listOfUsers.map { it.toUsers() }
                }
        }
    }



}