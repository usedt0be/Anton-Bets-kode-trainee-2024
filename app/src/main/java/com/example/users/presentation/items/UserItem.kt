package com.example.users.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.users.R
import com.example.users.presentation.User


@Composable
fun UserItem(modifier: Modifier,user: User, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = MaterialTheme.colors.background)
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(88.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = user.avatarUrl,
                    placeholder = painterResource(id = R.drawable.user_placeholder)
                ),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(72.dp)

            )
        }

        Column(
            modifier = Modifier
                .height(60.dp)
        ) {
            Row(
                modifier = Modifier
                    .height(26.dp)
                    .padding(top = 7.dp)
            ) {
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    modifier = Modifier
                        .height(20.dp)
                        .alignByBaseline(),
                    style = MaterialTheme.typography.h6
                    )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = user.userTag.lowercase(),
                    modifier = Modifier
                        .height(18.dp)
                        .alignByBaseline(),
                    style = MaterialTheme.typography.subtitle1
                )
            }
            Text(
                text = user.department.replaceFirstChar {
                    it.uppercase()}.replace("_", " "),
                fontSize = 13.sp,
                style = MaterialTheme.typography.caption
            )
        }

    }
}


@Preview
@Composable
fun UserItemPreview() {
    UserItem(
        modifier = Modifier,
        user = User(
            id = "1",
            avatarUrl = "jlahlf",
            firstName = "Алексей",
            lastName = "Миногаров",
            userTag = "mi",
            department = "Analyst",
            position = "1",
            phone = "80787009",
            birthday = "02.03.1982"
        ),
        onClick = {}
    )
}