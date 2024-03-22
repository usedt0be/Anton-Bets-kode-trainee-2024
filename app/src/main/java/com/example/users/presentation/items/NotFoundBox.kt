package com.example.users.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.users.R

@Composable
fun NothingWasFoundBox(modifier:Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxWidth()
        .height(198.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.nothing_found),
                contentDescription = "nothing found",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(56.dp)

            )
            Text(
                text = "Мы никого не нашли",
                modifier = Modifier
                    .padding(top = 6.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h4,
                lineHeight = 22.sp,
                fontSize = 17.sp
            )

            Text(
                text = "Попробуй скорректировать запрос",
                modifier = Modifier.padding(top=12.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.subtitle1,
                lineHeight = 20.sp
            )


        }

    }
}



@Preview
@Composable
fun NothingWasFoundBoxPreview() {
    NothingWasFoundBox()
}