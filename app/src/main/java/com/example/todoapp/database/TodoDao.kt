package com.example.todoapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapp.repository.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun getTodoListFlow(): Flow<List<TodoItem>>

    @Query("SELECT * FROM todo_table")
    fun getTodoList(): List<TodoItem>

    @Query("SELECT * FROM todo_table WHERE id = :id")
    fun getTodoItemById(id: String): TodoItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodoList(itemList: List<TodoItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodoItem(todo: TodoItem)

    @Delete
    fun deleteTodoItem(todo: TodoItem)

    @Query("DELETE FROM todo_table")
    fun dropTodoItems()
}