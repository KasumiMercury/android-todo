package com.example.androidtodo.listview

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidtodo.ui.theme.AndroidTodoTheme
import kotlin.math.roundToInt

@Composable
fun TodoList(
    modifier: Modifier = Modifier,
    viewModel: TodoViewModel = viewModel(),
    onTodoClick: (TodoItem) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val todos = mutableListOf<TodoItem>()

    when (uiState) {
        is UiState.Loading -> Text("Loading...")
        is UiState.Error -> TODO()
        is UiState.Success -> {
            todos.clear()
            todos.addAll((uiState as UiState.Success).todos)
        }
    }

    var showDialog by rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(todos, key = { todo -> todo.id() }) { todo ->
            TodoListItem(todo = todo, modifier = Modifier.clickable {
                onTodoClick(todo)
            })
        }
    }

    if (showDialog) {
        Dialog(
            onConfirm = {
                showDialog = false
            }
        )
    }
}

private enum class DeleteSwapState {
    INITIAL,
    OPENED,
    OVER_SWIPED
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoListItem(todo: TodoItem, modifier: Modifier = Modifier) {
    BoxWithConstraints {
        val maxWidthPx = with(LocalDensity.current) {
            maxWidth.toPx()
        }

        val deleteButtonWidth = 64.dp
        val deleteButtonWidthPx = with(LocalDensity.current) {
            deleteButtonWidth.toPx()
        }
        val velocityThreshold = with(LocalDensity.current) {
            125.dp.toPx()
        }

        val anchors = DraggableAnchors {
            DeleteSwapState.INITIAL at 0f
            DeleteSwapState.OPENED at deleteButtonWidthPx
            DeleteSwapState.OVER_SWIPED at maxWidthPx * 0.6f
        }

        Log.d("item", todo.id())
        Log.d("anchor", anchors.toString())

        val decayAnimationSpec = rememberSplineBasedDecay<Float>()

        val state = remember {
            AnchoredDraggableState(
                initialValue = DeleteSwapState.INITIAL,
                anchors = anchors,
                positionalThreshold = { distance: Float -> distance * 0.5f },
                velocityThreshold = { velocityThreshold },
                snapAnimationSpec = SpringSpec(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMediumLow
                ),
                decayAnimationSpec = decayAnimationSpec,
                confirmValueChange = {
                    when (it) {
                        DeleteSwapState.OVER_SWIPED -> false
                        else -> true
                    }
                }
            )
        }

        Box(
            modifier = Modifier
                .anchoredDraggable(
                    state = state,
                    orientation = Orientation.Horizontal,
                    reverseDirection = true
                )
                .wrapContentHeight()
        ) {

            // delete button
            Row(
                modifier = Modifier
                    .matchParentSize(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(deleteButtonWidth)
                        .background(MaterialTheme.colorScheme.error),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Delete",
                        color = MaterialTheme.colorScheme.onError,
                        modifier = Modifier.wrapContentSize(),
                        textAlign = TextAlign.Center
                    )
                }
            }

            // item
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset { IntOffset(-state.offset.roundToInt(), 0) }
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                ListItem(
                    headlineContent = { Text(todo.title()) },
                    supportingContent = {
                        Text(
                            text = todo.formattedDeadline(),
                            modifier = modifier
                        )
                    },
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun Dialog(onConfirm: () -> Unit = {}) {
    AlertDialog(
        text = { Text("Hello!") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Close")
            }
        },
        onDismissRequest = { /* handle dialog close event */ }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    val todos = listOf(
//        TodoItem("Buy milk", LocalDateTime.now().plusDays(1)),
//        TodoItem("Walk the dog", LocalDateTime.now().plusDays(2)),
//        TodoItem("Do homework", LocalDateTime.now().plusDays(3)),
//        TodoItem("Go to the gym", LocalDateTime.now().plusDays(4)),
//        TodoItem("Call mom", LocalDateTime.now().plusDays(5)),
//        TodoItem("Buy a present", LocalDateTime.now().plusDays(6)),
//        TodoItem("Read a book", LocalDateTime.now().plusDays(7)),
//        TodoItem("Go to the cinema", LocalDateTime.now().plusDays(8)),
//        TodoItem("Cook dinner", LocalDateTime.now().plusDays(9)),
//        TodoItem("Go to bed", LocalDateTime.now().plusDays(10)),
//        TodoItem("Wake up", LocalDateTime.now().plusDays(11)),
//        TodoItem("Go to work", LocalDateTime.now().plusDays(12)),
//        TodoItem("Go to the doctor", LocalDateTime.now().plusDays(13)),
//        TodoItem("Go to the dentist", LocalDateTime.now().plusDays(14)),
//        TodoItem("Go to the pharmacy", LocalDateTime.now().plusDays(15)),
//    )

    AndroidTodoTheme {
        TodoList()
    }
}
