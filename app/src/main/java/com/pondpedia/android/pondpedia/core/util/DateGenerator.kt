package com.pondpedia.android.pondpedia.core.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateGenerator {
    fun getCurrentDateTime(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        return LocalDateTime.now().format(formatter)
    }

    fun getCurrentDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return LocalDateTime.now().format(formatter)
    }
}