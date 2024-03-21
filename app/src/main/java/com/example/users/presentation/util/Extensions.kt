package com.example.users.presentation.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Extensions {
    fun String.toCalendar(): Calendar {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = formatter.parse(this) ?: Date()
        return calendar
    }
}