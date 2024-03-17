package com.example.users.presentation.items

data class TabUserItem(
    val departament:String
)

val tabItems = listOf(
    TabUserItem(departament = "Все"),
    TabUserItem(departament = "Designers"),
    TabUserItem(departament = "Analysts"),
    TabUserItem(departament = "Managers"),
    TabUserItem(departament = "IOS"),
    TabUserItem(departament = "Android")
)
