package com.example.androidtodo.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtodo.data.TaskItem
import com.example.androidtodo.data.TodoModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {
    private val model = TodoModel()
    private val items: MutableStateFlow<List<TaskItem>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            items.value = model.getTasks()
        }
    }

    val uiState: StateFlow<TaskListUiState> = items.map { todos ->
        if (todos.isEmpty()) {
            TaskListUiState.Loading
        } else {
            TaskListUiState.Success(todos)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TaskListUiState.Loading
    )
}