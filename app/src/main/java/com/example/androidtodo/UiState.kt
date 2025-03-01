package com.example.androidtodo

import com.example.androidtodo.listview.TodoItem

sealed interface UiState {
    data object Loading : UiState
    data class Success(
        val todos: List<TodoItem>
    ) : UiState

    data class Error(val message: String) : UiState
}