package com.example.users.presentation.util

sealed class Screens(val route: String) {
    object HomeScreen: Screens(route = "home_screen")
    object UsersScreen: Screens(route = "users_screen")
}