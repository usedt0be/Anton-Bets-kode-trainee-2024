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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.users.R
import com.example.users.presentation.viewmodel.HomeViewModel


@Composable
fun SearchBar(modifier: Modifier, homeViewModel: HomeViewModel) {
    var query by remember {
        mutableStateOf("")
    }
    var isActive by remember {
        mutableStateOf(false)
    }
    Log.d("searchbar", "$isActive")

    val scale by animateFloatAsState(targetValue = if (isActive) 0.8f else 1.0f)

    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        CustomTextField(
            value = query,
            onValueChange = {
                query = it
                homeViewModel.findUser(query.trim())
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search_is_active),
                    contentDescription = "cancel button",
                    tint = if (isActive) MaterialTheme.colors.primaryVariant
                    else MaterialTheme.colors.onSurface
                )
            },
            placeholder = {
                if(!isActive) {
                    Text(
                        text = "Введите имя, тег, почту...",
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            },
            modifier = Modifier
                .height(40.dp)
                .onFocusChanged { focusState -> isActive = focusState.isFocused }
                .fillMaxWidth(fraction = scale),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                isActive = false
            }),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.surface
            ),
            trailingIcon = {
                if (isActive) {
                    IconButton(onClick = { query = "" }) {
                        Icon(
                            painter = painterResource(id = R.drawable.cancel),
                            contentDescription = "cancel button",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                } else {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.list_ui_alt),
                            contentDescription = "filter button",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        )
        AnimatedVisibility(
            visible = isActive,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally(),
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(
                text = "Отмена",
                modifier = Modifier
                    .clickable {
                        focusManager.clearFocus()
                        isActive = false
                    }
                    .align(Alignment.CenterVertically),
                maxLines = 1,
                style = MaterialTheme.typography.h5
            )
        }
    }
}



//@Preview
//@Composable
//fun SearchBarPreview() {
//    SearchBar(modifier = Modifier)
//}