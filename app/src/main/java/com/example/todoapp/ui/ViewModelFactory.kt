package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.repository.TodoItemsRepository

class ViewModelFactory(private val repository: TodoItemsRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass) {
        TodosViewModel::class.java -> TodosViewModel(repository) as T
        AddItemViewModel::class.java -> AddItemViewModel(repository) as T
        else -> throw IllegalAccessException("Unknown ViewModel Class")
    }
}
