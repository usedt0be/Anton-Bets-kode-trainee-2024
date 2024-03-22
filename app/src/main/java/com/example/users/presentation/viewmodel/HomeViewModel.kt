package com.example.users.presentation.viewmodel

import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.example.users.presentation.util.Util
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


    private val _filteredByBirthdayUsers = MutableStateFlow<Map<String,List<User>>>(emptyMap())



    init {
        getUsers()
    }

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing

    private val _refreshingFailed =  MutableStateFlow(false)
    val refreshingFailed = _refreshingFailed

    private fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val usersFlow = getUsersFromDbUseCase.execute()
                usersFlow.collect { users ->
                    val mappedUsers = users.map { it.toUsers() }
                    Log.d("userList", "$users")
                    withContext(Dispatchers.IO) {
                        _usersList.value = mappedUsers
                        _notFilteredUsers.value = mappedUsers
                    }
                    Log.d("usrsNF", "${_notFilteredUsers.value}")
                }
            } catch (e: Exception) {
                val usersFlow = getUsersWithoutInternetUseCase.execute()
                usersFlow.collect { users ->
                    Log.d("internetOff", "$users")
                    withContext(Dispatchers.IO) {
                        _usersList.value = users.map { it.toUsers() }
                        _notFilteredUsers.value =users.map { it.toUsers() }
                    }
                    Log.d("usrsN", "${_usersList.value}")
                    Log.d("usrsNF", "${_notFilteredUsers.value}")
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
            Log.d("blank", "${query.isBlank()}")
            Log.d("blank", "${foundUsers}")
            updateUiWithUsers(foundUsers)
        }
    }


    private fun updateUiWithUsers(foundUsers: Flow<List<UserEntity>>) {
        viewModelScope.launch(Dispatchers.IO) {
                foundUsers.collect { listOfUsers ->
                    Log.d("searchedusers", "${listOfUsers}")
                    _usersList.value = listOfUsers.map { it.toUsers() }
                }
        }
    }

    fun refreshUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _refreshingFailed.value = false
            try {
                _isRefreshing.value = true
                val usersFlow = refreshUsersUseCase.execute()
                Log.d("newusers", "$usersFlow")
                usersFlow.collect{ users ->
                    Log.d("newusers", "$users")
                    withContext(Dispatchers.Main) {
                        _usersList.value = users.map { it.toUsers() }
                    }
                    isRefreshing.value = false
                }
            }catch (e: UnknownHostException) {
                _refreshingFailed.value = true
                Log.d("updateErr", "$e")
                _isRefreshing.value = false

            }
        }

    }


    fun sortUsersByAlphabetically(users: List<User>)  {
        if(filteredAlphabetically.value) {
            Log.d("fltrV", "${_filteredAlphabetically.value}")
            viewModelScope.launch(Dispatchers.IO) {
                val sorted = users.sortedWith(compareBy({ it.firstName }, { it.lastName }))
                Log.d("sortedusers", "$sorted")
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
            Log.d("fltrV", "${_filteredByBirthday.value}")
            viewModelScope.launch(Dispatchers.IO) {
                val sorted = users.toThisBirthdayYearList() + users.toNextYearBirthdayList()
                Log.d("sortedbrth", "$sorted")
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