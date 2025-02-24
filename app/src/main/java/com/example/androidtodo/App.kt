package com.example.androidtodo

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

object Route {
    @Serializable
    data object Todo

    @Serializable
    data class TodoDetail(
        val id: String
    )
}
