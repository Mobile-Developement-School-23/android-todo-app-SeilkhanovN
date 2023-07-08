package com.example.todoapp.repository

import androidx.room.Entity
import java.time.LocalDateTime

@Entity("todo_table")
data class TodoItem(
    var id: String,
    var text: String,
    var importance: Importance = Importance.NORMAL,
    var deadline: LocalDateTime? = null,
    var isDone: Boolean = false,
    var creationDate: LocalDateTime? = null,
    var modificationDate: LocalDateTime? = null
) {
    constructor() : this("-1", "")
}

enum class Importance {
    LOW, NORMAL, URGENT
}
