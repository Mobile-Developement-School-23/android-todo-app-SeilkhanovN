package com.example.todoapp.network

import com.google.gson.annotations.SerializedName

data class TodoItemApi(
    @SerializedName("id")
    var id: String,
    @SerializedName("text")
    var text: String,
    @SerializedName("importance")
    var importance: String,
    @SerializedName("deadline")
    var deadline: Long?,
    @SerializedName("done")
    var isDone: Boolean,
    @SerializedName("created_at")
    var creationDate: Long,
    @SerializedName("changed_at")
    var modificationDate: Long,
    @SerializedName("last_updated_by")
    var updatedBy: String
)
