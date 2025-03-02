package com.example.androidtodo.data

import java.time.LocalDateTime

class TodoModel {
    private val tasks = mutableListOf<TaskItem>(
        TaskItem("Buy milk", LocalDateTime.now().plusDays(1)),
        TaskItem("Walk the dog", LocalDateTime.now().plusDays(2)),
        TaskItem("Do homework", LocalDateTime.now().plusDays(3)),
        TaskItem("Go to the gym", LocalDateTime.now().plusDays(4)),
        TaskItem("Call mom", LocalDateTime.now().plusDays(5)),
        TaskItem("Buy a present", LocalDateTime.now().plusDays(6)),
        TaskItem("Read a book", LocalDateTime.now().plusDays(7)),
        TaskItem("Go to the cinema", LocalDateTime.now().plusDays(8)),
        TaskItem("Cook dinner", LocalDateTime.now().plusDays(9)),
        TaskItem("Go to bed", LocalDateTime.now().plusDays(10)),
        TaskItem("Wake up", LocalDateTime.now().plusDays(11)),
        TaskItem("Go to work", LocalDateTime.now().plusDays(12)),
        TaskItem("Go to the doctor", LocalDateTime.now().plusDays(13)),
        TaskItem("Go to the dentist", LocalDateTime.now().plusDays(14)),
        TaskItem("Go to the pharmacy", LocalDateTime.now().plusDays(15)),
    )

    suspend fun getTodos(): List<TaskItem> {
        return tasks
    }

    suspend fun getTaskById(id: String): TaskItem? {
        return tasks.find { it.id() == id }
    }
}
