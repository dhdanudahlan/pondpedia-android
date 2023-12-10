package com.pondpedia.android.pondpedia.core.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateGenerator {

    fun getCurrentDateTimeStr(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return LocalDateTime.now().format(formatter)
    }
    fun getCurrentDateStr(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return LocalDate.now().format(formatter)
    }
    fun getCurrentTimeStr(): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return LocalTime.now().format(formatter)
    }

    fun getCurrentDateTime(): String {
        return LocalDateTime.now().toString()
    }
    fun getCurrentDate(): String {
        return LocalDate.now().toString()
    }
    fun getCurrentTime(): String {
        return LocalTime.now().toString()
    }

    fun localDateTimeToString(localDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return localDateTime.format(formatter)
    }
}