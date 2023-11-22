package com.proyecto.navegacionnilanavarro.Models

data class ChatMessage(val senderId: String, val message: String, val timestamp: Long) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "senderId" to senderId,
            "message" to message,
            "timestamp" to timestamp
        )
    }
}