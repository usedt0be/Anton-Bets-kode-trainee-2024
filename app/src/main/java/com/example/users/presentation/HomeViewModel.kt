package com.example.users.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.users.data.dto.UsersDto
import com.example.users.data.repository.UsersRepositoryImpl
import com.example.users.data.source.remote.RetrofitInstance
import com.example.users.domain.entity.UsersEntity
import com.example.users.domain.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
        private val repositoryImpl: UsersRepositoryImpl
): ViewModel() {


    private val getUsersUseCase = GetUsersUseCase(repositoryImpl)

    private val _usersList = MutableStateFlow<List<UsersEntity>>(emptyList())
    val usersList = _usersList

    init {
        getUsers()
    }


    private fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _usersList.value = getUsersUseCase.execute()
        }
    }



}