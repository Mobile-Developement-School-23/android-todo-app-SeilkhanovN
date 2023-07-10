package com.example.todoapp.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity("todo_table")
data class TodoItem(
    @PrimaryKey
    var id: String,
    var text: String,
    var importance: Importance = Importance.NORMAL,
    var deadline: String? = null,
    var isDone: Boolean = false,
    var creationDate: String? = null,
    var modificationDate: String? = null
) {
    constructor() : this("-1", "")
}

enum class Importance {
    LOW, NORMAL, URGENT
}
