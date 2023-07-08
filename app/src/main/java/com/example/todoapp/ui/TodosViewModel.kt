package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import com.example.todoapp.repository.TodoItemsRepository

class TodosViewModel (val repository : TodoItemsRepository) : ViewModel() {

}