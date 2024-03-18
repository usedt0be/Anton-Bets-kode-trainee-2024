package com.example.users.presentation.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun UpdateErrorMessage(data: SnackbarData) {
    Snackbar(modifier = Modifier
        .padding(start = 16.dp,end = 16.dp, bottom = 40.dp)
        .height(64.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colors.error
        ) {
        Text(
            text = data.message,
            modifier = Modifier.padding(start = 16.dp, top = 14.dp, bottom = 14.dp),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.primary
        )
    }
}


//@Preview
//@Composable
//fun UpdateErrorMessagePreview(){
//    UpdateErrorMessage()
//}