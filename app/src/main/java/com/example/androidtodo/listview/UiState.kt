package com.example.androidtodo.listview

import com.example.androidtodo.data.TaskItem

sealed interface UiState {
    data object Loading : UiState
    data class Success(
        val todos: List<TaskItem>
    ) : UiState

    data class Error(val message: String) : UiState
}