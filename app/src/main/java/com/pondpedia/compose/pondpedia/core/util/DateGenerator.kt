package com.pondpedia.compose.pondpedia.core.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateGenerator {
    fun getCurrentDateTime(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm")

        return LocalDateTime.now().format(formatter)
    }

    fun getCurrentDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd")

        return LocalDateTime.now().format(formatter)
    }
}