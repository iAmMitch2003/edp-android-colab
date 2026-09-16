package com.liceo.liceochat.data.network

import com.liceo.liceochat.data.network.dto.MessageDto
import com.liceo.liceochat.data.network.dto.NewMessageDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatApiService {

    @GET("messages")
    suspend fun getMessages(
        @Query("sortBy") sortBy: String = "createdAt",
        @Query("order") order: String = "desc"
    ): List<MessageDto>

    @POST("messages")
    suspend fun sendMessage(
        @Body message: NewMessageDto
    ): Response<MessageDto>

    @DELETE("messages/{id}")
    suspend fun deleteMessage(
        @Path("id") id: String
    ): Response<Unit>
}