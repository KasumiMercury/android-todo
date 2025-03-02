package com.example.androidtodo

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidtodo.detail.TaskDetailScreen
import com.example.androidtodo.tasklist.TaskListScreen
import kotlinx.serialization.Serializable

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Todo
    ) {
        composable<Route.Todo> {
            Scaffold { innerPadding ->
                TaskListScreen(
                    modifier = Modifier.padding(innerPadding),
                    onTodoClick = { todo ->
                        navController.navigate(Route.TodoDetail(todo.id()))
                    }
                )
            }
        }

        composable<Route.TodoDetail> { navBackStackEntry ->
            Scaffold { innerPadding ->
                val todoId = navBackStackEntry.arguments?.getString("id") ?: ""
                TaskDetailScreen(
                    modifier = Modifier.padding(innerPadding),
                    taskId = todoId
                )
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
