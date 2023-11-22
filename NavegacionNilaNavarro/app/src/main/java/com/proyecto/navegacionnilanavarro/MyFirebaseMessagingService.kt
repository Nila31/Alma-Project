package com.proyecto.navegacionnilanavarro

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let { notification ->
            val title = notification.title
            val body = notification.body

            // Mostrar la notificación al usuario
            showNotification(title, body)
        }
    }

    private fun showNotification(title: String?, body: String?) {
        // Implementar la lógica para mostrar la notificación en la pantalla del usuario.
        // Esto puede incluir el uso de NotificationManager y NotificationCompat para crear y mostrar la notificación.
        // Puedes mostrar la notificación con un canal específico, un ícono personalizado, un sonido, etc.
    }
}