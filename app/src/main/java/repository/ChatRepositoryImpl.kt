package com.liceo.liceochat.data.repository

import com.liceo.liceochat.core.AppResult
import com.liceo.liceochat.data.network.ChatApiService
import com.liceo.liceochat.data.network.dto.MessageDto
import com.liceo.liceochat.data.network.dto.NewMessageDto
import com.liceo.liceochat.data.network.dto.toDomain
import com.liceo.liceochat.domain.ChatRepository
import com.liceo.liceochat.domain.Message
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.longOrNull
import kotlinx.serialization.json.contentOrNull
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class ChatRepositoryImpl(
    private val api: ChatApiService
) : ChatRepository {

    override suspend fun getMessages(): AppResult<List<Message>> {
        return safeCall {
            api.getMessages().toDomain()
        }
    }

    override suspend fun sendMessage(
        sender: String,
        text: String
    ): AppResult<Unit> {

        return try {
            val message = NewMessageDto(
                sender = sender.trim(),
                text = text.trim(),
                createdAt = System.currentTimeMillis()
            )

            var response = api.sendMessage(message)

            if (response.isSuccessful) {
                return AppResult.Success(Unit)
            }

            val errorBody = response.errorBody()?.string().orEmpty()

            if (
                response.code() == 400 &&
                errorBody.contains(
                    "max number of elements",
                    ignoreCase = true
                )
            ) {
                val messages = api.getMessages(
                    sortBy = "createdAt",
                    order = "desc"
                )

                val oldestMessage = messages
                    .filter { !it.id.isNullOrBlank() }
                    .minByOrNull { getCreatedAt(it) }

                if (oldestMessage?.id.isNullOrBlank()) {
                    return AppResult.Failure.Unknown(
                        "The chat is full and no old message can be removed."
                    )
                }

                val deleteResponse = api.deleteMessage(
                    oldestMessage.id!!
                )

                if (!deleteResponse.isSuccessful) {
                    return AppResult.Failure.Unknown(
                        "The chat is full. Please try again."
                    )
                }

                response = api.sendMessage(message)

                if (response.isSuccessful) {
                    return AppResult.Success(Unit)
                }

                return AppResult.Failure.Unknown(
                    "Could not send the message."
                )
            }

            AppResult.Failure.Unknown(
                "HTTP ${response.code()}: ${errorBody.ifBlank {
                    "The server rejected the message."
                }}"
            )

        } catch (e: UnknownHostException) {
            AppResult.Failure.NoInternet
        } catch (e: SocketTimeoutException) {
            AppResult.Failure.Timeout
        } catch (e: IOException) {
            AppResult.Failure.NoInternet
        } catch (e: Exception) {
            AppResult.Failure.Unknown(
                e.message ?: "Something went wrong."
            )
        }
    }

    private fun getCreatedAt(message: MessageDto): Long {
        val value = message.createdAt
            ?: return 0L

        val primitive = value as? JsonPrimitive
            ?: return 0L

        primitive.longOrNull?.let {
            return it
        }

        val dateString = primitive.contentOrNull
            ?: return 0L

        return try {
            val format = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.US
            )

            format.timeZone = TimeZone.getTimeZone("UTC")

            format.parse(dateString)?.time ?: 0L
        } catch (e: Exception) {
            0L
        }
    }

    private inline fun <T> safeCall(
        block: () -> T
    ): AppResult<T> {
        return try {
            AppResult.Success(block())
        } catch (e: UnknownHostException) {
            AppResult.Failure.NoInternet
        } catch (e: SocketTimeoutException) {
            AppResult.Failure.Timeout
        } catch (e: IOException) {
            AppResult.Failure.NoInternet
        } catch (e: Exception) {
            AppResult.Failure.Unknown(
                e.message ?: "Something went wrong."
            )
        }
    }
}