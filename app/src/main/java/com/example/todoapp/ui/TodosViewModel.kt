package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.repository.TodoItem
import com.example.todoapp.repository.TodoItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class TodosViewModel (val repository : TodoItemsRepository) : ViewModel() {

    private var _todoList = MutableSharedFlow<List<TodoItem>>()
    val todoList: SharedFlow<List<TodoItem>> get() = _todoList.asSharedFlow()

    init {
        takeAllItems()
    }
    fun takeAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            _todoList.emitAll(repository.tododao.getTodoListFlow())
        }

    }

}