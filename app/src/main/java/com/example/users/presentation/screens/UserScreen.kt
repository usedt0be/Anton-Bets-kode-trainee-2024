package com.example.users.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.users.R
import com.example.users.presentation.util.Screens
import com.example.users.presentation.util.Util



@Composable
fun UserScreen(
    navController: NavController, avatarUrl: String, firstName: String,
    lastName: String, department: String,
    birthday: String, userTag: String, phone: String
) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.surface)
                .fillMaxHeight(0.3f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                IconButton(
                    onClick = { navController.navigate(Screens.HomeScreen.route) {
                        popUpTo(Screens.HomeScreen.route) {inclusive = true}
                    } },
                    modifier = Modifier
                        .size(36.dp)
                        .padding(start = 10.dp, top = 12.dp))
                {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "back to home screen",
                    )
                }

            }

            Image(
                painter = rememberAsyncImagePainter(
                    model = avatarUrl,
                    placeholder = painterResource(
                        id = R.drawable.user_placeholder
                    )
                ),
                contentDescription = "user photo",
                modifier = Modifier
                    .size(104.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$firstName $lastName",
                    maxLines = 1,
                    fontSize = 24.sp,
                    lineHeight = 24.sp,
                    style = MaterialTheme.typography.h1
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = userTag.lowercase(),
                    style = MaterialTheme.typography.h3,
                    lineHeight = 17.sp,
                    fontSize = 17.sp
                )
            }

            Text(
                text = department.replaceFirstChar {
                    it.uppercase()
                }.replace("_", " "),
                style = MaterialTheme.typography.caption,
                lineHeight = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp)
            )

        }
        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = "fav icon",

                    )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = birthday,
                    style = MaterialTheme.typography.h6,
                    color = Color(0xff050510),
                    modifier = Modifier.padding()
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = Util.calculateAge(birthday),
                    style = MaterialTheme.typography.h6,
                    lineHeight = 20.sp,
                    color = Color(0XFF97979B),
                    modifier = Modifier.padding(end = 4.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable { Util.makePhoneCall(phone, context) },
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = "phone icon",
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = phone,
                    style = MaterialTheme.typography.h6,
                    color = Color(0xff050510),
                    modifier = Modifier.padding()
                )
            }
        }
    }
}






@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun UserScreenPreview() {
    val navController = rememberNavController()
    UserScreen(
        navController = navController ,
        avatarUrl = "_",
        firstName = "Robert",
        lastName ="Jackson",
        department = "designer",
        birthday = "2000-05-10",
        userTag ="rj" ,
        phone ="032507028"
    )
}