package com.example.users.presentation.screens
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.users.presentation.User
import com.example.users.presentation.items.FilterBottomSheet
import com.example.users.presentation.items.SearchBar
import com.example.users.presentation.items.UpdateErrorMessage
import com.example.users.presentation.items.UserItem
import com.example.users.presentation.items.tabItems
import com.example.users.presentation.util.Screens
import com.example.users.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController) {
    val users = homeViewModel.usersList.collectAsState().value
    Log.d("usrs", "$users")


    val fltralp by remember {
        homeViewModel.filteredAlphabetically
    }
    Log.d("fltralp", "$fltralp")
    Log.d("fltr", "${homeViewModel.filteredAlphabetically}")

        LaunchedEffect(fltralp) {
            homeViewModel.sortUsersByAlphabetically(users)
        }

//    val ysers = if (filteredAlphabetically) {
//        homeViewModel.sortUsersByAlphabetically(users)
//    } else {
//        users
//    }
//    Log.d("ysers", "$ysers")
//    LaunchedEffect(fltralp) {
//            homeViewModel.sortUsersByAlphabetically(users)
//    }

//    if(filteredAlphabetically) {
//
//    }



    val query = rememberSaveable { mutableStateOf("") }

    val pagerState = rememberPagerState { tabItems.size }
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    val refreshing by homeViewModel.isRefreshing.collectAsState()

    val refreshingFailed = homeViewModel.refreshingFailed.collectAsState().value
    Log.d("isref", "$refreshingFailed")

    val refreshState = rememberPullRefreshState(refreshing = refreshing,
        onRefresh = { homeViewModel.refreshUsers() }
    )

    val snackBarHostState by remember { mutableStateOf(SnackbarHostState()) }

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val sheetScope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            Column {
                Box(
                    Modifier.padding(start = 16.dp, top = 6.dp, end = 16.dp)
                ) {
                    SearchBar(
                        query = query,
                        searchUser = { homeViewModel.findUser(query.value) },
                        openSheet = {
                            sheetScope.launch { sheetState.show() }
                        }
                    )
                }
                ScrollableTabRow(
                    selectedTabIndex = selectedTabIndex,
                    edgePadding = 0.dp,
                    backgroundColor = MaterialTheme.colors.primary,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            color = MaterialTheme.colors.primaryVariant,
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
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) { data ->
                UpdateErrorMessage(message = data.message)
            }
        },
    )
    { paddingValues ->
        Box(modifier = Modifier.pullRefresh(refreshState)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                ) { index ->
                    val currentTab by remember { mutableStateOf(tabItems[index].departament) }
                    val filteredUsers = remember(users, currentTab) {
                        users.filter { user ->
                            when (currentTab) {
                                "Все" -> true
                                "Designers" -> user.department == "design"
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
                            UserItem(
                                user = user,
                                modifier = Modifier,
                                onClick = {
                                    navController.navigate(
                                        Screens.UsersScreen.route +
                                                "?userId=${user.id}&avatarUrl=${user.avatarUrl}&firstName=${user.firstName}&lastName=${user.lastName}&userTag=${user.userTag}&department=${user.department}&birthday=${user.birthday}&phone=${user.phone}"
                                    )
                                }
                            )
                        }
                    }
                }
                if (refreshingFailed) {
                    LaunchedEffect(true) {
                        snackBarHostState.showSnackbar(
                            "Не могу обновить данные.\n" +
                                    "Проверь соединение с интернетом."
                        )
                    }
                }

            }
            PullRefreshIndicator(
                refreshing = refreshing,
                state = refreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                backgroundColor = MaterialTheme.colors.secondary
            )
        }
        FilterBottomSheet(
            state = sheetState,
            filteredAlphabetically = fltralp,
            alphabetFilterIsActive = {homeViewModel.filteredAlphabetically.value = it}
        )
    }
}

