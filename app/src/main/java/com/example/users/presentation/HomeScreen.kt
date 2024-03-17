package com.example.users.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.users.presentation.items.UserItem
import com.example.users.presentation.items.tabItems
import com.example.users.presentation.viewmodel.HomeViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val users = homeViewModel.usersList.collectAsState().value


    val pagerState = rememberPagerState { tabItems.size }
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp,
            backgroundColor = MaterialTheme.colors.primary,
            indicator = {tabPositions ->
                TabRowDefaults.Indicator(
                    color = MaterialTheme.colors.primaryVariant ,
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                )
            }

        ) {
            tabItems.forEachIndexed { index, tabUserItem ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = tabUserItem.departament,
                            maxLines = 1,
                            style = if (index == selectedTabIndex) {
                                MaterialTheme.typography.h4
                            } else {
                                MaterialTheme.typography.subtitle2
                            }
                        )
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) {index ->
            val currentTab by remember { mutableStateOf(tabItems[index].departament) }
            val filteredUsers = remember(users, currentTab) {
                users.filter { user ->
                    when(currentTab) {
                        "Все" -> true
                        "Designers" -> user.department=="design"
                        "Analysts" -> user.department == "analytics"
                        "Managers" -> user.department == "management"
                        "IOS" -> user.department == "ios"
                        "Android" -> user.department == "android"
                        else -> false
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(filteredUsers) { user ->
                    UserItem(user = user)
                }
            }
        }
    }
}


