package com.proyecto.navegacionnilanavarro.Repositorios

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.proyecto.navegacionnilanavarro.Models.Pendiente

class PendientesRepository {
    private val firestore = Firebase.firestore
    private val collection = firestore.collection("pendientes")

    suspend fun guardarPendiente(pendiente: Pendiente) {
        collection.add(pendiente)
    }

    suspend fun eliminarPendiente(id: String) {
        Log.d("PendientesRepository", "Eliminando pendiente con ID: $id")
        collection.document(id).delete()
    }
}