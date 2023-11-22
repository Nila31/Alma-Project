package com.proyecto.navegacionnilanavarro.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.proyecto.navegacionnilanavarro.Models.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel(private val chat: CollectionReference) : ViewModel() {

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages = _chatMessages.asStateFlow()

    init {
        subscribeToChatMessages()
    }

    private fun subscribeToChatMessages() {
        chat.addSnapshotListener { snapshot, error ->
            if (error != null) {
                // Manejar el error
                return@addSnapshotListener
            }

            val messages = snapshot?.toObjects(ChatMessage::class.java) ?: emptyList()
            _chatMessages.value = messages
        }
    }

    fun sendMessage(senderId: String, message: String) {
        val chatMessage = ChatMessage(senderId, message, System.currentTimeMillis())

        // Se utiliza un nuevo documento para cada mensaje
        chat.add(chatMessage.toMap())
    }}