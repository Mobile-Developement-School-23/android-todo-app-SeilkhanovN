package com.example.todoapp.network

import com.google.gson.annotations.SerializedName

data class ListApiRequest (
    @SerializedName("list")
    val list : List<TodoItemApi>
)