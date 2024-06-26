package com.example.users.presentation.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@Composable
fun FilterBottomSheet(
    hide:() -> Unit,
    filteredAlphabetically: Boolean,
    alphabetFilterIsActive:(Boolean) -> Unit,
    filteredByBirthday: Boolean,
    birthdayFilterIsActive:(Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    val scope = rememberCoroutineScope()
    var alphabetFilter by remember { mutableStateOf(filteredAlphabetically) }
    var birthdayFilter by remember { mutableStateOf(filteredByBirthday) }

    Column(modifier = Modifier
        .height(218.dp)
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "Сортировка",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding( top = 24.dp),
            style = MaterialTheme.typography.h2,
            lineHeight = 24.sp,
            fontWeight = FontWeight.W600,
            fontSize = 20.sp
        )

        Row(
            modifier = Modifier
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            RadioButton(
                selected = alphabetFilter,
                onClick = {
                    alphabetFilter = !alphabetFilter
                    alphabetFilterIsActive(alphabetFilter)
                    birthdayFilterIsActive(false)
                    birthdayFilter = false
                    scope.launch {
                        hide()
                    }
                },
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " По алфавиту",
                style = MaterialTheme.typography.h6,
                lineHeight = 20.sp,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 14.dp)
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            RadioButton(
                selected = birthdayFilter,
                onClick = {
                    birthdayFilter = !birthdayFilter
                    birthdayFilterIsActive(birthdayFilter)
                    alphabetFilter = false
                    alphabetFilterIsActive(false)
                    scope.launch {
                        hide()
                    }
                },
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " По дню рождения",
                style = MaterialTheme.typography.h6,
                lineHeight = 20.sp,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 14.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }

}


@Preview
@Composable
fun FilterBottomPreview() {
    FilterBottomSheet(
        filteredAlphabetically = true,
        alphabetFilterIsActive = {},
        filteredByBirthday = true,
        birthdayFilterIsActive = {},
        hide = {}
    )
}