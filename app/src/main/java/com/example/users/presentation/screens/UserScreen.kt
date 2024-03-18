package com.example.users.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.users.R
import com.example.users.presentation.User


@Composable
fun UserScreen(user: User) {


        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding()
                .background(color = MaterialTheme.colors.surface)
        ) {
            Box(
                modifier = Modifier
                    .height(28.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "back to home screen",
                    modifier = Modifier.padding (start = 10.dp , top = 12.dp)
                )
            }

            Image(
                painter = rememberAsyncImagePainter(
                    model = user.avatarUrl,
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
                    text = "${user.firstName} ${user.lastName}",
                    maxLines = 1,
                    style = MaterialTheme.typography.h2,
                    fontSize = 24.sp,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = user.userTag,
                    style = MaterialTheme.typography.h3,
                    fontSize = 17.sp,
                )
            }

            Text(
                text = user.department,
                style = MaterialTheme.typography.caption,
                lineHeight = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
            Icon(
                painter = painterResource(id = R.drawable.favorite),
                contentDescription = "fav icon",
                modifier = Modifier.alignByBaseline()
            )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = user.birthday,
                    style = MaterialTheme.typography.h6,
                    color = Color(0xff050510),
                    modifier = Modifier.padding()
                )

            }

            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
            Icon(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "fav icon",
                modifier = Modifier.alignByBaseline()
            )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = user.phone,
                    style = MaterialTheme.typography.h6,
                    color = Color(0xff050510),
                    modifier = Modifier.padding()
                )
            }
        }

}





@Preview
@Composable
fun UserScreenPreview() {
    UserScreen(user = User(
        "1",
        "32",
        "Robert" ,
        "Jackson",
        "rj",
        "designer",
        "Technician",
        "16.05.1999",
        "032507028"
        )
    )
}