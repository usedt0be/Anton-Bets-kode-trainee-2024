package com.example.users.presentation.items

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.users.presentation.User
import com.example.users.presentation.util.Extensions.toNextYearBirthdayList
import com.example.users.presentation.util.Extensions.toThisBirthdayYearList
import com.example.users.presentation.util.Screens



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilteredByBirthdayColumn(
    users: List<User>, navController: NavController,
) {
    val thisYearList = users.toThisBirthdayYearList()
    val nextYearList = users.toNextYearBirthdayList()

    Log.d("yearListTHIS", "$thisYearList")
    Log.d("yearListNEXT", "${nextYearList}")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        items(thisYearList) { user ->
            UserItem(
                user = user,
                modifier = Modifier,
                onClick = {
                    navController.navigate(
                        Screens.UsersScreen.route +
                                "?userId=${user.id}&avatarUrl=${user.avatarUrl}&firstName=${user.firstName}&lastName=${user.lastName}&userTag=${user.userTag}&department=${user.department}&birthday=${user.birthday}&phone=${user.phone}"
                    )
                }
            )
        }
        stickyHeader {
            Text(text = "2025", fontSize = 28.sp)
        }
        items(nextYearList) { user ->
            UserItem(
                user = user,
                modifier = Modifier,
                onClick = {
                    navController.navigate(
                        Screens.UsersScreen.route +
                                "?userId=${user.id}&avatarUrl=${user.avatarUrl}&firstName=${user.firstName}&lastName=${user.lastName}&userTag=${user.userTag}&department=${user.department}&birthday=${user.birthday}&phone=${user.phone}"
                    )
                }
            )
        }
    }


}







