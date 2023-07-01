package com.example.todoapp.network

import com.google.gson.annotations.SerializedName

data class GetListResponse(
    @SerializedName("status")
    val status : String,
    @SerializedName("list")
    val list : List<TodoItemApi>,
    @SerializedName("revision")
    val revision : Int
)
