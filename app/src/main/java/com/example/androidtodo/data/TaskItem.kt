package com.example.androidtodo.data

import android.text.format.DateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.UUID

data class TaskItem(val title: String, val deadline: LocalDateTime) {
    private val locale = Locale.getDefault()
    private val pattern: String = DateFormat.getBestDateTimePattern(locale, "yyyy/MM/dd HH:mm")
    private val formattedDeadline: String =
        deadline.format(DateTimeFormatter.ofPattern(pattern).withLocale(locale))

    private val id = UUID.randomUUID().toString()

    fun id() = id
    fun title() = title
    fun formattedDeadline() = formattedDeadline
}
