package com.example.users

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.users.presentation.screens.HomeScreen
import com.example.users.presentation.screens.UserScreen
import com.example.users.presentation.util.Screens
import com.example.users.presentation.viewmodel.HomeViewModel
import com.example.users.ui.theme.UsersTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            UsersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screens.HomeScreen.route
                    ) {
                        composable(route = Screens.HomeScreen.route) {
                            HomeScreen(homeViewModel = homeViewModel, navController = navController)
                        }
                        composable(
                            route = Screens.UsersScreen.route +
                                    "?userId={userId}&avatarUrl={avatarUrl}&firstName={firstName}" +
                                    "&lastName={lastName}&userTag={userTag}&department={department}" +
                                    "&birthday={birthday}&phone={phone}",
                            arguments = listOf(
                                navArgument(name = "avatarUrl") {
                                    type = NavType.StringType
                                    defaultValue = "45783785"
                                },
                                navArgument(name = "firstName") {
                                    type = NavType.StringType
                                    defaultValue = "Rob"
                                },
                                navArgument(name = "lastName") {
                                    type = NavType.StringType
                                    defaultValue = "Johnson"
                                },
                                navArgument(name = "userTag") {
                                    type = NavType.StringType
                                    defaultValue = "rj"
                                },
                                navArgument(name = "department") {
                                    type = NavType.StringType
                                    defaultValue = "Android"
                                },
                                navArgument(name = "birthday") {
                                    type = NavType.StringType
                                    defaultValue = "16-05-1999"
                                },
                                navArgument(name = "phone") {
                                    type = NavType.StringType
                                    defaultValue = "94579431"
                                },
                            )
                        ) {

                            val avatarUrl = it.arguments?.getString("avatarUrl") ?: "45783785"
                            val firstName = it.arguments?.getString("firstName") ?: "Rob"
                            val lastName = it.arguments?.getString("lastName") ?: "Johnson"
                            val userTag = it.arguments?.getString("userTag") ?: "rj"
                            val department = it.arguments?.getString("department") ?: "Android"
                            val birthday = it.arguments?.getString("birthday") ?: "16-05-1999"
                            val phone = it.arguments?.getString("phone") ?: "94579431"

                            UserScreen(
                                navController = navController,
                                avatarUrl = avatarUrl,
                                firstName = firstName,
                                lastName = lastName,
                                userTag = userTag,
                                department = department,
                                birthday = birthday,
                                phone = phone
                            )
                        }
                    }
                }
            }
        }
    }
}



