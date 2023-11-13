package com.pondpedia.compose.pondpedia.core.util

object StringParser {
    fun commaToDot(text: String): String {
        val cleanedText = text.replace(Regex("[,]"), ".")
        return cleanedText
    }
}