package com.example.androidtodo.detail

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

class TaskDetailViewModel(
    private val taskId: String
) : ViewModel() {
    private val model = TodoModel()
    private val item: MutableStateFlow<TaskItem?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            item.value = model.getTaskById(taskId)
        }
    }

    val uiState: StateFlow<TaskDetailUiState> = item.map { task ->
        if (task == null) {
            TaskDetailUiState.Loading
        } else {
            TaskDetailUiState.Success(task)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = TaskDetailUiState.Loading
    )
}