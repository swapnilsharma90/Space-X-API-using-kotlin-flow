package com.swap.spacex.utils

import java.text.SimpleDateFormat
import java.util.*

fun getDateFromTimestamp(timestamp: String): String? {
    val apiFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
    val formattedDate = apiFormat.parse(timestamp)
    val desiredFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss a", Locale.ENGLISH)

    return desiredFormat.format(formattedDate)
}

