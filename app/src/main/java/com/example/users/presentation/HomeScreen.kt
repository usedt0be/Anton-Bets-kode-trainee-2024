package com.example.users.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import com.example.users.presentation.items.SearchBar
import com.example.users.presentation.items.UserItem
import com.example.users.presentation.viewmodel.HomeViewModel


@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val users = homeViewModel.usersList.collectAsState().value

    Scaffold(
        modifier = Modifier.padding(start = 16.dp, top = 6.dp, end = 16.dp),
        topBar = {
            SearchBar(modifier = Modifier.padding(start = 16.dp,top = 6.dp ,end = 6.dp,))
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        )
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
//                    .padding(start = 16.dp, top = 156.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(users) { user ->
                    UserItem(user = user)
                }
            }
        }

    }

}