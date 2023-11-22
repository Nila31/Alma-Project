package com.proyecto.navegacionnilanavarro.ViewModel

import android.icu.util.Calendar
import android.icu.util.TimeUnit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.navegacionnilanavarro.NotificationWorker
import com.proyecto.navegacionnilanavarro.Models.Reminder
import kotlinx.coroutines.launch

class ReminderViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()

    fun saveReminder(reminder: Reminder) {
        viewModelScope.launch {
            // Guardar el recordatorio en Firestore
            firestore.collection("recordatorios").add(reminder)

            // Programar la notificación
            scheduleNotification(reminder)
        }
    }

    private fun scheduleNotification(reminder: Reminder) {
        // Obtener la hora actual
        val currentTime = Calendar.getInstance()

        // Calcular el tiempo de la notificación en base a la frecuencia seleccionada
        val notificationTime = when (reminder.timeFrequency) {
            "Ahora" -> currentTime.timeInMillis // Si es "Ahora", la notificación será inmediata
            "En 1 minuto" -> currentTime.timeInMillis + (1 * 60 * 1000) // 1 minuto en milisegundos
            "En 5 minutos" -> currentTime.timeInMillis + (5 * 60 * 1000) // 5 minutos en milisegundos
            "En 30 minutos" -> currentTime.timeInMillis + (30 * 60 * 1000) // 30 minutos en milisegundos
            "En 1 hora" -> currentTime.timeInMillis + (60 * 60 * 1000) // 1 hora en milisegundos
            "En 2 horas" -> currentTime.timeInMillis + (2 * 60 * 60 * 1000) // 2 horas en milisegundos
            "En 4 horas" -> currentTime.timeInMillis + (4 * 60 * 60 * 1000) // 4 horas en milisegundos
            "En 6 horas" -> currentTime.timeInMillis + (6 * 60 * 60 * 1000) // 6 horas en milisegundos
            "En 8 horas" -> currentTime.timeInMillis + (8 * 60 * 60 * 1000) // 8 horas en milisegundos
            "En 12 horas" -> currentTime.timeInMillis + (12 * 60 * 60 * 1000) // 8 horas en milisegundos


            else -> currentTime.timeInMillis // Valor predeterminado: notificación inmediata
        }

        // Crear un Data con la información del recordatorio y la hora de la notificación
        val inputData = Data.Builder()
            .putString("reminderTitle", reminder.medicationName)
            .putString(
                "reminderBody",
                "Hora de tomar: ${reminder.dosage}"
            )
            .build()

        // Construir el trabajo para programar la notificación
        val notificationWork = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(inputData)
            .setInitialDelay(notificationTime - currentTime.timeInMillis, java.util.concurrent.TimeUnit.MILLISECONDS)
            .build()

        // Programar el trabajo con WorkManager
        WorkManager.getInstance().enqueue(notificationWork)
    }
}