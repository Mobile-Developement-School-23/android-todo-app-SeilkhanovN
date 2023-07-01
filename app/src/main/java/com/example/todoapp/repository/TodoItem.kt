package com.example.todoapp.repository

import java.time.LocalDateTime

data class TodoItem(
    var id: String,
    var text: String,
    var importance: Importance = Importance.NORMAL,
    var deadline: LocalDateTime? = null,
    val isDone: Boolean = false,
    val creationDate: LocalDateTime? = null,
    val modificationDate: LocalDateTime? = null
)

enum class Importance {
    LOW, NORMAL, URGENT
}
