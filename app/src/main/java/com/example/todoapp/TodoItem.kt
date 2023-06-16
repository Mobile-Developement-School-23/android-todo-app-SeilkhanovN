package com.example.todoapp

import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDateTime
import java.util.Date

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
