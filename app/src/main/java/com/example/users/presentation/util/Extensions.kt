package com.example.users.presentation.util

import com.example.users.presentation.User
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

    fun List<User>.toNextYearBirthdayList(
        today: Calendar = Calendar.getInstance(),
        currentYear:Int = today.get(Calendar.YEAR)
    ): List<User> {
        val birthdayNextYearList = mutableListOf<User>()
        this.forEach {user ->
            val userBirthday = user.birthday.toCalendar()
            val userBirthdayThisYear = userBirthday.clone() as Calendar
            userBirthdayThisYear.set(Calendar.YEAR, currentYear)

            if (userBirthdayThisYear.before(today)) {
                birthdayNextYearList.add(user)
            }
        }
        return birthdayNextYearList.sortedBy {
            it.birthday.toCalendar().get(Calendar.DAY_OF_YEAR)
        }
    }


    fun List<User>.toThisBirthdayYearList(
        today: Calendar = Calendar.getInstance(),
        currentYear:Int = today.get(Calendar.YEAR)
    ): List<User> {
        val birthdayThisYearList = mutableListOf<User>()
        this.forEach {user ->
            val userBirthday = user.birthday.toCalendar()
            val userBirthdayThisYear = userBirthday.clone() as Calendar
            userBirthdayThisYear.set(Calendar.YEAR, currentYear)

            if (userBirthdayThisYear.after(today)) {
                birthdayThisYearList.add(user)
            }
        }
        return birthdayThisYearList.sortedBy {
            it.birthday.toCalendar().get(Calendar.DAY_OF_YEAR)
        }
    }
}