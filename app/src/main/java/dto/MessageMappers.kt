package com.liceo.liceochat.data.network.dto

import com.liceo.liceochat.domain.Message
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.longOrNull
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun MessageDto.toDomain(): Message {
    return Message(
        id = id ?: "",
        sender = sender ?: "Unknown",
        text = text ?: "",
        createdAt = parseCreatedAt(createdAt)
    )
}

fun List<MessageDto>.toDomain(): List<Message> {
    return map { it.toDomain() }
}

private fun parseCreatedAt(value: JsonElement?): Long {
    if (value == null) {
        return 0L
    }

    val primitive = value as? JsonPrimitive ?: return 0L

    primitive.longOrNull?.let {
        return it
    }

    val dateString = primitive.contentOrNull ?: return 0L

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