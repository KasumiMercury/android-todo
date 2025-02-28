package com.example.androidtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.androidtodo.ui.theme.AndroidTodoTheme
import java.time.LocalDateTime

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
