package com.example.users.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.users.presentation.items.UserItem
import com.example.users.presentation.viewmodel.HomeViewModel


@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val users = homeViewModel.usersList.collectAsState().value


    Column(modifier = Modifier.fillMaxSize()) {
           LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(users) {user ->
                    UserItem(user = user)
                }
           }
    }
}