package com.example.androidtodo.detail

sealed interface TaskDetailUiState {
    data object Loading : TaskDetailUiState
    data class Success(val id: String) : TaskDetailUiState
    data class Error(val message: String) : TaskDetailUiState
}