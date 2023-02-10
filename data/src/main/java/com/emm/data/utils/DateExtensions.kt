package com.emm.data.utils

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

const val RELEASE_STATE_PATTERN = "dd MMM yyyy"

fun String.toLocalDate(): LocalDate? {
    return try {
        val dtf = DateTimeFormatter.ofPattern(
            RELEASE_STATE_PATTERN,
            Locale.ENGLISH,
        )

        LocalDate.parse(this, dtf)
    } catch (_: Exception) {
        null
    }
}

fun LocalDate.toEpochMillis(): Long {
    return this.atStartOfDay()
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()
}

fun String.releaseStateToEpochMillis(): Long =
    toLocalDate()?.toEpochMillis() ?: 0L
