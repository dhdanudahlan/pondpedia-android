package com.pondpedia.android.pondpedia.core.util

import kotlin.math.abs

object StringParser {

    private fun commaToDot(text: String): String {
        val regexPattern = Regex(",")
        return text.replace(regexPattern, ".")
    }
    private fun removeNonNumeric(text: String): String {
        val regexPattern = Regex("[^0-9,.-]")
        return text.replace(regexPattern, "")
    }

    fun toInt(text: String): Int? {
        val regexPattern = Regex("[^0-9-]")
        return text.replace(regexPattern, "").replace(regexPattern, "").replaceFirst("-", "+").replace("-", "").replaceFirst("+", "-").toIntOrNull()
    }
    fun toIntAbs(text: String): Int? {
        val result = toInt(text)
        return if (result != null) abs(result) else null
    }
    fun toFloat(text: String): Float? {
        val regexPattern = Regex("[^0-9-,.]")
        return text.replace(regexPattern, "").replace(",", ".").replaceFirst("-", "+").replace("-", "").replaceFirst("+", "-").toFloatOrNull()
    }
    fun toFloatAbs(text: String): Float? {
        val result = toFloat(text)
        return if (result != null) abs(result) else null
    }

}