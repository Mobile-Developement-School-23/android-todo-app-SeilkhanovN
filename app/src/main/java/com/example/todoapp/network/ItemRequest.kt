package com.example.todoapp.network

import com.google.gson.annotations.SerializedName

data class ItemRequest(
    @SerializedName("status")
    var status: String,
    @SerializedName("element")
    var element: TodoItemApi
)
