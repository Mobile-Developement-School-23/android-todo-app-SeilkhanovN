package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import com.example.todoapp.repository.TodoItem
import com.example.todoapp.repository.TodoItemsRepository

class AddItemViewModel (val repository: TodoItemsRepository) : ViewModel() {
    var currentItem = TodoItem()

    fun addItem(todo : TodoItem) {
        repository.addItemToDatabase(todo)
    }

}
