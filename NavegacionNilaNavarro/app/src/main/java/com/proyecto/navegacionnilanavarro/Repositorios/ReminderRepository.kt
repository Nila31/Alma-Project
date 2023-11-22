package com.proyecto.navegacionnilanavarro.Repositorios

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.proyecto.navegacionnilanavarro.Models.Reminder

class ReminderRepository {
    private val firestore = Firebase.firestore
    private val collection = firestore.collection("recordatorios")

    suspend fun saveReminder(reminder: Reminder) {
        collection.add(reminder)
    }
}