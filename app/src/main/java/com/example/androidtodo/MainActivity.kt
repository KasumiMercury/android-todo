package com.example.androidtodo

import android.os.Bundle
import android.text.format.DateFormat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidtodo.ui.theme.AndroidTodoTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.UUID.randomUUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTodoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class TodoItem(val title: String, val deadline: LocalDateTime) {
    private val locale = Locale.getDefault()
    private val pattern: String = DateFormat.getBestDateTimePattern(locale, "yyyy/MM/dd HH:mm")
    private val formattedDeadline: String =
        deadline.format(DateTimeFormatter.ofPattern(pattern).withLocale(locale))

    private val id = randomUUID().toString()

    fun id() = id
    fun title() = title
    fun formattedDeadline() = formattedDeadline
}

@Composable
fun TodoListItem(todo: TodoItem, modifier: Modifier = Modifier) {
    ListItem(
        headlineContent = { Text(todo.title()) },
        supportingContent = {
            Text(
                text = todo.formattedDeadline(),
                modifier = modifier
            )
        },
    )
}

@Composable
fun TodoList(todos: List<TodoItem> = emptyList()) {
    LazyColumn {
        items(todos, key = { todo -> todo.id() }) { todo ->
            TodoListItem(todo = todo)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val todos = listOf(
        TodoItem("Buy milk", LocalDateTime.now().plusDays(1)),
        TodoItem("Walk the dog", LocalDateTime.now().plusDays(2)),
        TodoItem("Do homework", LocalDateTime.now().plusDays(3))
    )

    AndroidTodoTheme {
//        Greeting("Android")
        TodoList(todos = todos)
    }
}