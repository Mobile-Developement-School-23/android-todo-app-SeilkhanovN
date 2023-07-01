package com.example.todoapp.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiTodos {
    @GET("list")
    suspend fun getList(): Response<GetListResponse>

    @PATCH("list")
    suspend fun patchList(
        @Header("X-Last-Known-Revision") revision : Int,
        @Body list : ItemRequest
    ): Response<GetListResponse>

    @GET("list/{id}")
    suspend fun getElement(
        @Path("id") id : String
    ): Response<GetItemResponse>

    @POST("list")
    suspend fun postElement(
        @Header("X-Last-Known-Revision") revision: Int,
        @Body element: ItemRequest
    ): Response<GetItemResponse>

    @PUT("list/{id}")
    suspend fun updateElement(
        @Path("id") id: String,
        @Header("X-Last-Known-Revision") revision: Int,
        @Body item: ItemRequest
    ): Response<GetItemResponse>

    @DELETE("list/{id}")
    suspend fun deleteElement(
        @Path("id") id: String,
        @Header("X-Last-Known-Revision") revision: Int,
    ): Response<GetItemResponse>
}

