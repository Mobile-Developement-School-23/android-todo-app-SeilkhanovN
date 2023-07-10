package com.example.todoapp

import android.app.Application
import com.example.todoapp.database.TodoDao
import com.example.todoapp.database.TodoDatabase
import com.example.todoapp.repository.TodoItemsRepository

class App : Application() {
    lateinit var repository: TodoItemsRepository

    override fun onCreate() {
        super.onCreate()
        repository = TodoItemsRepository(
            TodoDatabase.getInstance(this).todoDatabaseDao()
        )
    }
}