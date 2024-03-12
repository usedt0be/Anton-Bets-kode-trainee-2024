package com.example.users.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.users.data.mappers.toUsers
import com.example.users.data.repository.UsersRepositoryImpl
import com.example.users.domain.usecases.GetUsersFromDbUseCase
import com.example.users.presentation.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
       private val repositoryImpl: UsersRepositoryImpl
): ViewModel() {


    private val getUsersFromDbUseCase = GetUsersFromDbUseCase(repositoryImpl)

    private val _usersList = MutableStateFlow<List<User>>(emptyList())
    val usersList = _usersList

    init {
        getUsersFromDb()
    }




    private fun getUsersFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val usersFlow = getUsersFromDbUseCase.execute()
            usersFlow.collect {usersList ->
                _usersList.value = usersList.map { it.toUsers() }
            }

        }
    }



}