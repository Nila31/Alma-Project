package com.proyecto.navegacionnilanavarro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.Models.ChatMessage
import com.proyecto.navegacionnilanavarro.ViewModel.ChatViewModel

@Composable
fun ChatScreen(navController: NavController, chatViewModel: ChatViewModel) {
    val chatMessages by chatViewModel.chatMessages.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ChatView(messages = chatMessages)
        SendMessageInput(chatViewModel)
    }
}

@Composable
fun ChatView(messages: List<ChatMessage>) {
    LazyColumn {
        items(messages) { message ->
            MessageItem(message)
        }
    }
}

@Composable
fun MessageItem(message: ChatMessage) {
    Text(text = "${message.senderId}: ${message.message}")
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SendMessageInput(chatViewModel: ChatViewModel) {
    var message by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Escribe un mensaje...") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onSend = {
                    if (message.isNotBlank()) {
                        chatViewModel.sendMessage("userId", message)
                        message = ""
                        keyboardController?.hide()
                    }
                }
            ),
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
    }
}