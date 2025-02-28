package com.example.androidtodo

import java.time.LocalDateTime

class TodoModel {
    suspend fun getTodos(): List<TodoItem> {
        return listOf(
            TodoItem("Buy milk", LocalDateTime.now().plusDays(1)),
            TodoItem("Walk the dog", LocalDateTime.now().plusDays(2)),
            TodoItem("Do homework", LocalDateTime.now().plusDays(3)),
            TodoItem("Go to the gym", LocalDateTime.now().plusDays(4)),
            TodoItem("Call mom", LocalDateTime.now().plusDays(5)),
            TodoItem("Buy a present", LocalDateTime.now().plusDays(6)),
            TodoItem("Read a book", LocalDateTime.now().plusDays(7)),
            TodoItem("Go to the cinema", LocalDateTime.now().plusDays(8)),
            TodoItem("Cook dinner", LocalDateTime.now().plusDays(9)),
            TodoItem("Go to bed", LocalDateTime.now().plusDays(10)),
            TodoItem("Wake up", LocalDateTime.now().plusDays(11)),
            TodoItem("Go to work", LocalDateTime.now().plusDays(12)),
            TodoItem("Go to the doctor", LocalDateTime.now().plusDays(13)),
            TodoItem("Go to the dentist", LocalDateTime.now().plusDays(14)),
            TodoItem("Go to the pharmacy", LocalDateTime.now().plusDays(15)),
        )
    }
}
