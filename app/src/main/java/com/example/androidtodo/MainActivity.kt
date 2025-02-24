package com.example.androidtodo

import android.os.Bundle
import android.text.format.DateFormat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidtodo.ui.theme.AndroidTodoTheme
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.UUID.randomUUID

private val todos = listOf(
    TodoItem("Buy milk", LocalDateTime.now().plusDays(1)),
    TodoItem("Walk the dog", LocalDateTime.now().plusDays(2)),
    TodoItem("Do homework", LocalDateTime.now().plusDays(3)),
    TodoItem("Go to the gym", LocalDateTime.now().plusDays(4)),
    TodoItem("Call mom", LocalDateTime.now().plusDays(5)),
    TodoItem("Buy a present", LocalDateTime.now().plusDays(6)),
    TodoItem("Read a book", LocalDateTime.now().plusDays(7)),
    TodoItem("Go to the cinema", LocalDateTime.now().plusDays(8)),
    TodoItem("Cook dinner", LocalDateTime.now().plusDays(9)),
    TodoItem("Go to bed", LocalDateTime.now().plusDays(10)),
    TodoItem("Wake up", LocalDateTime.now().plusDays(11)),
    TodoItem("Go to work", LocalDateTime.now().plusDays(12)),
    TodoItem("Go to the doctor", LocalDateTime.now().plusDays(13)),
    TodoItem("Go to the dentist", LocalDateTime.now().plusDays(14)),
    TodoItem("Go to the pharmacy", LocalDateTime.now().plusDays(15)),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTodoTheme {
                App()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    TodoList(todos = todos, modifier = Modifier.padding(innerPadding))
//                }
            }
        }
    }
}

object Route {
    @Serializable
    data object Todo

    @Serializable
    data class TodoDetail(
        val id: String
    )
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Todo
    ) {
        composable<Route.Todo> {
            Scaffold { innerPadding ->
                TodoList(
                    todos = todos,
                    modifier = Modifier.padding(innerPadding),
                    onTodoClick = { todo ->
                        navController.navigate(Route.TodoDetail(todo.id()))
                    })
            }
        }

        composable<Route.TodoDetail> { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id") ?: ""
            val todo = todos.find { it.id() == id } ?: return@composable
            Scaffold { innerPadding ->
                TodoDetail(title = todo.title(), modifier = Modifier.padding(innerPadding))
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
        modifier = modifier
    )
}

@Composable
fun TodoList(
    modifier: Modifier = Modifier,
    todos: List<TodoItem> = emptyList(),
    onTodoClick: (TodoItem) -> Unit = {}
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(todos, key = { todo -> todo.id() }) { todo ->
            TodoListItem(todo = todo, modifier = Modifier.clickable {
                onTodoClick(todo)
            })
        }
    }

    if (showDialog) {
        Dialog(
            onConfirm = {
                showDialog = false
            }
        )
    }
}

@Composable
fun TodoDetail(modifier: Modifier = Modifier, title: String) {
    Text(text = title, modifier = modifier)
}

@Composable
fun Dialog(onConfirm: () -> Unit = {}) {
    AlertDialog(
        text = { Text("Hello!") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Close")
            }
        },
        onDismissRequest = { /* handle dialog close event */ }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val todos = listOf(
        TodoItem("Buy milk", LocalDateTime.now().plusDays(1)),
        TodoItem("Walk the dog", LocalDateTime.now().plusDays(2)),
        TodoItem("Do homework", LocalDateTime.now().plusDays(3)),
        TodoItem("Go to the gym", LocalDateTime.now().plusDays(4)),
        TodoItem("Call mom", LocalDateTime.now().plusDays(5)),
        TodoItem("Buy a present", LocalDateTime.now().plusDays(6)),
        TodoItem("Read a book", LocalDateTime.now().plusDays(7)),
        TodoItem("Go to the cinema", LocalDateTime.now().plusDays(8)),
        TodoItem("Cook dinner", LocalDateTime.now().plusDays(9)),
        TodoItem("Go to bed", LocalDateTime.now().plusDays(10)),
        TodoItem("Wake up", LocalDateTime.now().plusDays(11)),
        TodoItem("Go to work", LocalDateTime.now().plusDays(12)),
        TodoItem("Go to the doctor", LocalDateTime.now().plusDays(13)),
        TodoItem("Go to the dentist", LocalDateTime.now().plusDays(14)),
        TodoItem("Go to the pharmacy", LocalDateTime.now().plusDays(15)),
    )

    AndroidTodoTheme {
//        Greeting("Android")
        TodoList(todos = todos)
    }
}