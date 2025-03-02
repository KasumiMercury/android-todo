package com.example.androidtodo.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidtodo.data.TaskItem
import java.time.LocalDateTime

@Composable
fun TodoDetail(modifier: Modifier = Modifier, title: String) {
    Text(text = title, modifier = modifier)
}

@Composable
fun TaskDetailScreen(
    modifier: Modifier = Modifier,
    taskId: String,
) {
    val viewModel: TaskDetailViewModel = viewModel{
        TaskDetailViewModel(taskId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is TaskDetailUiState.Loading -> Text("Loading...")
        is TaskDetailUiState.Error -> Text("Error")
        is TaskDetailUiState.Success -> {
            TaskDetailComponent(
                modifier = modifier,
                task = (uiState as TaskDetailUiState.Success).task
            )
        }
    }
}

@Composable
fun TaskDetailComponent(
    modifier: Modifier = Modifier,
    task: TaskItem
) {
    Column {
        Text(text = task.title, modifier = modifier)
        Text(text = task.formattedDeadline(), modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailPreview() {
    TaskDetailComponent(
        task = TaskItem(
            title = "Task 1",
            deadline = LocalDateTime.now()
        )
    )
}