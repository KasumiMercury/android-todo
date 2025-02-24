package com.example.androidtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.androidtodo.ui.theme.AndroidTodoTheme
import java.time.LocalDateTime

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
