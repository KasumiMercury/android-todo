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

val todos = listOf(
    TaskItem("Buy milk", LocalDateTime.now().plusDays(1)),
    TaskItem("Walk the dog", LocalDateTime.now().plusDays(2)),
    TaskItem("Do homework", LocalDateTime.now().plusDays(3)),
    TaskItem("Go to the gym", LocalDateTime.now().plusDays(4)),
    TaskItem("Call mom", LocalDateTime.now().plusDays(5)),
    TaskItem("Buy a present", LocalDateTime.now().plusDays(6)),
    TaskItem("Read a book", LocalDateTime.now().plusDays(7)),
    TaskItem("Go to the cinema", LocalDateTime.now().plusDays(8)),
    TaskItem("Cook dinner", LocalDateTime.now().plusDays(9)),
    TaskItem("Go to bed", LocalDateTime.now().plusDays(10)),
    TaskItem("Wake up", LocalDateTime.now().plusDays(11)),
    TaskItem("Go to work", LocalDateTime.now().plusDays(12)),
    TaskItem("Go to the doctor", LocalDateTime.now().plusDays(13)),
    TaskItem("Go to the dentist", LocalDateTime.now().plusDays(14)),
    TaskItem("Go to the pharmacy", LocalDateTime.now().plusDays(15)),
)
