package com.example.users.presentation.viewmodel

import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.users.data.mappers.toUsers
import com.example.users.data.repository.UsersRepositoryImpl
import com.example.users.data.source.local.room.UserEntity
import com.example.users.domain.usecases.GetUsersFromDbUseCase
import com.example.users.domain.usecases.GetUsersWithoutInternetUseCase
import com.example.users.domain.usecases.RefreshUsersUseCase
import com.example.users.presentation.User
import com.example.users.presentation.util.Extensions.toNextYearBirthdayList
import com.example.users.presentation.util.Extensions.toThisBirthdayYearList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
       private val repositoryImpl: UsersRepositoryImpl
): ViewModel() {

    private val getUsersFromDbUseCase = GetUsersFromDbUseCase(repositoryImpl)
    private val refreshUsersUseCase = RefreshUsersUseCase(repositoryImpl)
    private val getUsersWithoutInternetUseCase = GetUsersWithoutInternetUseCase(repositoryImpl)


    private var _usersList = MutableStateFlow<List<User>>(emptyList())
    var usersList : StateFlow<List<User>> = _usersList


    private var _notFilteredUsers = MutableStateFlow<List<User>>(emptyList())


    private var _filteredAlphabetically = mutableStateOf(false)
    var filteredAlphabetically = _filteredAlphabetically

    private var _filteredByBirthday = mutableStateOf(false)
    val filteredByBirthday = _filteredByBirthday


    init {
        getUsers()
    }
    private val _notFound = mutableStateOf(false)
    val notFound = _notFound

    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing = _isRefreshing

    private val _refreshingFailed =  mutableStateOf(false)
    val refreshingFailed = _refreshingFailed

    private fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val usersFlow = getUsersFromDbUseCase.execute()
                usersFlow.collect { users ->
                    val mappedUsers = users.map { it.toUsers() }
                    withContext(Dispatchers.IO) {
                        _usersList.value = mappedUsers
                        _notFilteredUsers.value = mappedUsers
                    }
                }
            } catch (e: Exception) {
                val usersFlow = getUsersWithoutInternetUseCase.execute()
                usersFlow.collect { users ->
                    withContext(Dispatchers.IO) {
                        _usersList.value = users.map { it.toUsers() }
                        _notFilteredUsers.value =users.map { it.toUsers() }
                    }
                }
            } catch (e: IOException) {
                error(e)
            }
        }
    }

    fun findUser(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val foundUsers = if (query.isBlank()) {
                getUsersWithoutInternetUseCase.execute()
            } else {
                try {
                    repositoryImpl.findUsers("*$query*")
                } catch (e:SQLiteException) {
                    getUsersWithoutInternetUseCase.execute()
                }
            }

            updateUiWithUsers(foundUsers)
        }
    }


    private fun updateUiWithUsers(foundUsers: Flow<List<UserEntity>>) {
        viewModelScope.launch(Dispatchers.IO) {
                foundUsers.collect { listOfUsers ->
                    if (listOfUsers.isEmpty()) {
                        _notFound.value = true
                    } else {_notFound.value = false}
                        _usersList.value = listOfUsers.map { it.toUsers() }
                    if(filteredAlphabetically.value) {
                        filterUsersAlphabetically(_usersList.value)
                    } else if(filteredByBirthday.value) {
                        filterUsersByBirthDay(_usersList.value)
                    }

                }
        }
    }

    fun refreshUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _refreshingFailed.value = false
            try {
                _isRefreshing.value = true
                val usersFlow = refreshUsersUseCase.execute()
                updateUiWithUsers(usersFlow)
                isRefreshing.value = false

            }catch (e: UnknownHostException) {
                _refreshingFailed.value = true
                _isRefreshing.value = false
            }
        }
    }

    fun filterUsersAlphabetically(users: List<User>)  {
        if(filteredAlphabetically.value) {
            viewModelScope.launch(Dispatchers.IO) {
                val sorted = users.sortedWith(compareBy({ it.firstName }, { it.lastName }))
               withContext(Dispatchers.Main) {
                    _usersList.value = sorted
                }
            }
        }
        else {
           viewModelScope.launch(Dispatchers.IO) {
                _usersList.value = _notFilteredUsers.value
           }
        }
    }

    suspend fun filterUsersByBirthDay(users: List<User>) {
        if (filteredByBirthday.value) {
            viewModelScope.launch(Dispatchers.IO) {
                val sorted = users.toThisBirthdayYearList() + users.toNextYearBirthdayList()
                withContext(Dispatchers.Main) {
                    _usersList.value = sorted
                }
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                _usersList.value = _notFilteredUsers.value
            }
        }
    }
}