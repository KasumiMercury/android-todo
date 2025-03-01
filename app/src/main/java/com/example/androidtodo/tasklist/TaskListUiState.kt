package com.example.androidtodo.tasklist

import com.example.androidtodo.data.TaskItem

sealed interface TaskListUiState {
    data object Loading : TaskListUiState
    data class Success(
        val tasks: List<TaskItem>
    ) : TaskListUiState

    data class Error(val message: String) : TaskListUiState
}