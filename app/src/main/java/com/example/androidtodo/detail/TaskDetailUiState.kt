package com.example.androidtodo.detail

import com.example.androidtodo.data.TaskItem

sealed interface TaskDetailUiState {
    data object Loading : TaskDetailUiState
    data class Success(
        val task: TaskItem
    ) : TaskDetailUiState
    data class Error(val message: String) : TaskDetailUiState
}