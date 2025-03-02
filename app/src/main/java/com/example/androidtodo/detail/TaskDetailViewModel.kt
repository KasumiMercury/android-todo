package com.example.androidtodo.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtodo.data.TaskItem
import com.example.androidtodo.data.TodoModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    private val taskId: String
) : ViewModel() {
    private val model = TodoModel()
    private val item: MutableState<TaskItem?> = mutableStateOf(null)

    init {
        viewModelScope.launch {
            item.value = model.getTaskById(taskId)
        }
    }

    val uiState: () -> TaskDetailUiState =  {
        if (item.value == null) {
            TaskDetailUiState.Loading
        } else {
            TaskDetailUiState.Success(item.value!!)
        }
    }
}