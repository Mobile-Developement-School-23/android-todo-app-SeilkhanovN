package com.example.todoapp.network

import com.google.gson.annotations.SerializedName

data class GetItemResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("element")
    val element: TodoItemApi,
    @SerializedName("revision")
    val revision: Int
)
