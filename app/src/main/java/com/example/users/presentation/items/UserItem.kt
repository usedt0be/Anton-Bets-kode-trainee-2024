package com.example.users.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.users.presentation.User


@Composable
fun UserItem(user: User){

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp, end = 8.dp)
        .height(128.dp),
//        .clickable { onClick() },
        contentColor = MaterialTheme.colors.onPrimary

    ) {
        Row(modifier = Modifier
            .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(painter = rememberAsyncImagePainter(model = user.avatarUrl),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(90.dp)
                    .border(
                        width = 2.dp, color = MaterialTheme.colors.onPrimary,
                        CircleShape
                    )
            )

            Column(modifier = Modifier
                .padding(start = 28.dp)
                .fillMaxHeight()
            ) {
                Row() {
                    Text(text ="${user.firstName} ${user.lastName}",
                        modifier = Modifier.padding(top = 16.dp),
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.primary

                    )
                    Text(text = "${user.userTag}",
                        modifier = Modifier.padding(start = 4.dp),
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = user.department,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}

