package com.example.users.presentation.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@Composable
fun StickyHeader() {
    val nextYear = Calendar.getInstance().get(Calendar.YEAR) + 1
    Column(
        modifier = Modifier
            .height(68.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Divider(
                modifier = Modifier
                    .size(height = 1.dp, width = 72.dp),
                color = Color(0xffC3C3C6)
            )
            Text(
                text = "$nextYear",
                style = MaterialTheme.typography.subtitle2,
                lineHeight = 20.sp,
                color = Color(0xffC3C3C6)
            )
            Divider(
                modifier = Modifier
                    .size(height = 1.dp, width = 72.dp),
                color = Color(0xffC3C3C6)
            )
        }

    }

}



@Preview
@Composable
fun StickyHeaderPreview() {
    StickyHeader()
}