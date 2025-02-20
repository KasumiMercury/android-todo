package com.example.androidtodo

import android.os.Bundle
import android.text.format.DateFormat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

@Composable
fun TodoListItem(title: String, deadline: LocalDateTime, modifier: Modifier = Modifier) {
    val locale = Locale.getDefault()
    val pattern = DateFormat.getBestDateTimePattern(locale, "yyyy/MM/dd HH:mm")
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = {
            Text(
                text = deadline.format(DateTimeFormatter.ofPattern(pattern).withLocale(locale)),
                modifier = modifier
            )
        },
    )
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
    AndroidTodoTheme {
//        Greeting("Android")
        TodoListItem("Title", LocalDateTime.now())
    }
}