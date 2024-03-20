package com.example.users.presentation.viewmodel

import android.database.sqlite.SQLiteException
import android.util.Log
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject
enum class STATE {
    LOADING,
    SUCCESS,
    FAILED
}

@HiltViewModel
class HomeViewModel @Inject constructor(
       private val repositoryImpl: UsersRepositoryImpl
): ViewModel() {


    private val getUsersFromDbUseCase = GetUsersFromDbUseCase(repositoryImpl)
    private val refreshUsersUseCase = RefreshUsersUseCase(repositoryImpl)
    private val getUsersWithoutInternetUseCase = GetUsersWithoutInternetUseCase(repositoryImpl)

    private var state by mutableStateOf(STATE.LOADING)

    private val _usersList = MutableStateFlow<List<User>>(emptyList())
    val usersList = _usersList

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
                state = STATE.LOADING
                val usersFlow = getUsersFromDbUseCase.execute()

                usersFlow.collect { users ->
                    Log.d("userList", "$users")
                    withContext(Dispatchers.IO) {
                        _usersList.value = users.map { it.toUsers() }
                    }
                }
                state = STATE.SUCCESS
            } catch (e: Exception) {
//                state = STATE.LOADING
                val usersFlow = getUsersWithoutInternetUseCase.execute()
                usersFlow.collect { users ->
                    Log.d("internetOff", "$users")
                    withContext(Dispatchers.Main) {
                        _usersList.value = users.map { it.toUsers() }
                    }
                    state = STATE.SUCCESS
                }
            } catch (e: IOException) {
                state = STATE.FAILED
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



}