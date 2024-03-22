package com.example.users.presentation.items

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.users.R
import com.example.users.ui.theme.inter


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchBar(searchUser:(String) -> Unit,
              query: MutableState<String>,
              openSheet: () -> Unit,
              filterIsActive: MutableState<Boolean>) {

    var searchIsActive by remember {
        mutableStateOf(false)
    }
    Log.d("searchbar", "$searchIsActive")

    val scale by animateFloatAsState(targetValue = if (searchIsActive) 0.8f else 1.0f)

    val focusManager = LocalFocusManager.current

    var filter by remember {
        filterIsActive
    }
    Log.d("fltrICON" ,"$filter")

    Row(
        modifier = Modifier.height(52.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        CustomTextField(
            value = query.value,
            onValueChange = {
                query.value = it
                searchUser(query.value)
            },
            textStyle = TextStyle(
                fontFamily = inter,
                fontWeight = FontWeight.W500,
                fontSize = 15.sp,
                lineHeight = 20.sp
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search_is_active),
                    contentDescription = "cancel button",
                    tint = if (searchIsActive) MaterialTheme.colors.onPrimary
                    else MaterialTheme.colors.onSurface
                )
            },
            placeholder = {
                if(!searchIsActive) {
                    Text(
                        text = "Введите имя, тег, почту...",
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            },
            modifier = Modifier
                .height(40.dp)
                .onFocusChanged { focusState -> searchIsActive = focusState.isFocused }
                .fillMaxWidth(fraction = scale),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                searchIsActive = false
            }),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primaryVariant,
                backgroundColor = MaterialTheme.colors.surface
            ),
            trailingIcon = {
                if (searchIsActive) {
                    IconButton(onClick = {
                        query.value = ""
                        searchUser("")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.cancel),
                            contentDescription = "cancel button",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                } else {
                    IconButton(onClick = {
                        openSheet()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter_is_not_active),
                            contentDescription = "filter button",
                            tint = if (filter) MaterialTheme.colors.primaryVariant
                            else MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        )
        AnimatedVisibility(
            visible = searchIsActive,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally(),
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(
                text = "Отмена",
                modifier = Modifier
                    .clickable {
                        query.value = ""
                        searchUser("")
                        focusManager.clearFocus()
                        searchIsActive = false
                    }
                    .align(Alignment.CenterVertically),
                maxLines = 1,
                style = MaterialTheme.typography.h5
            )
        }


    }
}



@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(
        searchUser = {},
        query = remember { mutableStateOf("") },
        openSheet = {},
        filterIsActive = remember {
            mutableStateOf(true)
        }
    )
}