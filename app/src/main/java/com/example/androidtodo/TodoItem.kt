package com.example.androidtodo

import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidtodo.ui.theme.AndroidTodoTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.UUID

data class TodoItem(val title: String, val deadline: LocalDateTime) {
    private val locale = Locale.getDefault()
    private val pattern: String = DateFormat.getBestDateTimePattern(locale, "yyyy/MM/dd HH:mm")
    private val formattedDeadline: String =
        deadline.format(DateTimeFormatter.ofPattern(pattern).withLocale(locale))

    private val id = UUID.randomUUID().toString()

    fun id() = id
    fun title() = title
    fun formattedDeadline() = formattedDeadline
}

@Preview(showBackground = true)
@Composable
fun TodoItemPreview() {
    AndroidTodoTheme {
        TodoListItem(
            todo = TodoItem("Buy milk", LocalDateTime.now().plusDays(1))
        )
    }
}