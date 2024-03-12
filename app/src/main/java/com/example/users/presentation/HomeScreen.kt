package com.example.users.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.users.presentation.viewmodel.HomeViewModel


@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val users = homeViewModel.usersList.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = users.toString())
           LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(users) {
                    Text(text = it.toString())
                }
           }

    }
}