package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import com.example.todoapp.repository.TodoItem

class AddItemViewModel : ViewModel() {
    var currentItem = TodoItem()

}
