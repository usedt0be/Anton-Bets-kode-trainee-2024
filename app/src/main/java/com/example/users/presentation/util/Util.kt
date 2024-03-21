package com.example.users.presentation.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.users.presentation.User
import com.example.users.presentation.util.Extensions.toCalendar
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Util {
    fun calculateAge(birthDate: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateOfBirth = sdf.parse(birthDate)
        val dob = Calendar.getInstance()
        dob.time = dateOfBirth

        val today = Calendar.getInstance()

        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        val ageSuffix = when {
            age in 11..14 -> "лет"
            age % 10 == 1 -> "год"
            age % 10 in 2..4 -> "года"
            else -> "лет"
        }
        return "$age $ageSuffix"
    }


    fun makePhoneCall(phoneNumber: String, context: Context) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(intent)
    }



//    fun filterUsersByBirthday(
//        users: List<User>,
//        today: Calendar = Calendar.getInstance(),
//        currentYear:Int = today.get(Calendar.YEAR)
//    ):Map<String, List<User>> {
//        val nextYear = currentYear + 1
//        val birthdayThisYear = mutableListOf<User>()
//        val birthdayNextYear = mutableListOf<User>()
//        users.forEach { user ->
//            val userBirthday = user.birthday.toCalendar()
//
//            val userBirthdayThisYear = userBirthday.clone() as Calendar
//            userBirthdayThisYear.set(Calendar.YEAR, currentYear)
//
//            if (userBirthdayThisYear.before(today)) {
//                birthdayNextYear.add(user)
//            } else {
//                birthdayThisYear.add(user)
//            }
//        }
//
//        val d = birthdayThisYear.sortedBy {
//            it.birthday.toCalendar().get(Calendar.DAY_OF_YEAR)
//        }
//
//        return mapOf(
//            currentYear.toString() to birthdayThisYear.sortedBy {
//                it.birthday.toCalendar().get(Calendar.DAY_OF_YEAR)
//            },
//            nextYear.toString() to birthdayThisYear.sortedBy {
//                it.birthday.toCalendar().get(Calendar.DAY_OF_YEAR)
//            }
//        )
//    }


//    fun filterUsersByBirthday(
//        users: List<User>,
//        today: Calendar = Calendar.getInstance(),
//        currentYear:Int = today.get(Calendar.YEAR)
//    ):List<List<User>> {
//        val nextYear = currentYear + 1
//        val birthdayThisYear = mutableListOf<User>()
//        val birthdayNextYear = mutableListOf<User>()
//        users.forEach { user ->
//            val userBirthday = user.birthday.toCalendar()
//
//            val userBirthdayThisYear = userBirthday.clone() as Calendar
//            userBirthdayThisYear.set(Calendar.YEAR, currentYear)
//
//            if (userBirthdayThisYear.before(today)) {
//                birthdayNextYear.add(user)
//            } else {
//                birthdayThisYear.add(user)
//            }
//        }
//
//        return listOf(
//            birthdayThisYear.sortedBy {
//                it.birthday.toCalendar().get(Calendar.DAY_OF_YEAR)
//            },
//            birthdayNextYear.sortedBy {
//                it.birthday.toCalendar().get(Calendar.DAY_OF_YEAR)
//            }
//        )
////        return mapOf(
////            currentYear.toString() to birthdayThisYear.sortedBy {
////                it.birthday.toCalendar().get(Calendar.DAY_OF_YEAR)
////            },
////            nextYear.toString() to birthdayThisYear.sortedBy {
////                it.birthday.toCalendar().get(Calendar.DAY_OF_YEAR)
////            }
////        )
//    }

//    fun filterUsersByBirthday(
//        users: List<User>,
//        today: Calendar = Calendar.getInstance(),
//        currentYear:Int = today.get(Calendar.YEAR)
//    ):List<User> {
//        val nextYear = currentYear + 1
//        val birthdayThisYear = mutableListOf<User>()
//        val birthdayNextYear = mutableListOf<User>()
//        users.forEach { user ->
//            val userBirthday = user.birthday.toCalendar()
//
//            val userBirthdayThisYear = userBirthday.clone() as Calendar
//            userBirthdayThisYear.set(Calendar.YEAR, currentYear)
//
//            if (userBirthdayThisYear.before(today)) {
//                birthdayNextYear.add(user)
//            } else {
//                birthdayThisYear.add(user)
//            }
//        }
//
//    }





}