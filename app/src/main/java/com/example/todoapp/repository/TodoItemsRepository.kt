package com.example.todoapp.repository

import com.example.todoapp.database.TodoDao

class TodoItemsRepository (
    val tododao: TodoDao
        )  {

    fun addItemToDatabase(todo : TodoItem) {
        tododao.insertTodoItem(todo)
    }

    fun deleteItemFromDatabase(todo : TodoItem) {
        tododao.deleteTodoItem(todo)
    }



//    val todoitems : MutableList<TodoItem> = mutableListOf()
//    init {
//        todoitems.apply {
//            add(TodoItem("1","Список продуктов для покупки в магазине", Importance.LOW))
//            add(TodoItem("2","Прибраться в комнате", Importance.LOW))
//            add(TodoItem("3","Сходить в фитнес-центр", Importance.LOW))
//            add(TodoItem("4","Снять и обработать новое видео", Importance.URGENT))
//            add(TodoItem("5","Провести исследование по заданной теме", Importance.NORMAL))
//            add(TodoItem("6","Забронировать билеты на поездку", Importance.URGENT))
//            add(TodoItem("7","Приготовить вкусный ужин", Importance.NORMAL))
//            add(TodoItem("8","Прочитать новую книгу", Importance.NORMAL))
//            add(TodoItem("9","Организовать встречу с друзьями", Importance.URGENT))
//            add(TodoItem("10","Сходить на прогулку в парк", Importance.URGENT))
//            add(TodoItem("11","Сходить на прогулку в парк с Уланом", Importance.URGENT))
//        }
//
//    }


}