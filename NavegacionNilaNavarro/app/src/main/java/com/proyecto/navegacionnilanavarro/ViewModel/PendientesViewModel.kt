package com.proyecto.navegacionnilanavarro.ViewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.navegacionnilanavarro.Models.Pendiente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PendientesViewModel : ViewModel() {
    // Utilizamos State para notificar a la interfaz de usuario sobre cambios en la lista
    private val _pendientesList = mutableStateListOf<Pendiente>()
    val pendientesList: List<Pendiente>
        get() = _pendientesList

    // Función para agregar un pendiente a la lista y almacenarlo en Firestore
    // Función para agregar un pendiente a la lista y almacenarlo en Firestore
    fun guardarPendiente(userId: String, pendiente: Pendiente) {
        // Agregar el pendiente primero a la lista local
        _pendientesList.add(pendiente)

        // Guardar el pendiente en Firestore
        val db = FirebaseFirestore.getInstance()
        val pendientesCollection = db.collection("pendientes")
        pendientesCollection
            .add(pendiente)
            .addOnSuccessListener { documentReference ->
                // Actualizar la lista local con el nuevo ID del pendiente después de agregarlo a Firestore
                val newPendiente = pendiente.copy(id = documentReference.id)
                val index = _pendientesList.indexOf(pendiente)
                if (index != -1) {
                    _pendientesList[index] = newPendiente
                }
                Log.d("PendientesViewModel", "Pendiente agregado con ID: ${documentReference.id}")

                // Imprimir los pendientes actuales en el Logcat
                for (p in _pendientesList) {
                    Log.d("PendientesViewModel", "Pendiente: ${p.titulo}, ID: ${p.id}")
                }
            }
            .addOnFailureListener { e ->
                Log.e("PendientesViewModel", "Error al agregar el pendiente", e)
                // Si hubo un error al agregar el pendiente a Firestore, también lo eliminamos de la lista local
                _pendientesList.remove(pendiente)
            }
    }

    fun eliminarPendiente(pendiente: Pendiente) {
        val db = FirebaseFirestore.getInstance()
        val pendientesCollection = db.collection("pendientes")

        CoroutineScope(Dispatchers.IO).launch {
            if (pendiente.id.isNotEmpty()) {
                try {
                    pendientesCollection.document(pendiente.id).delete().await()
                    _pendientesList.remove(pendiente)
                    Log.d("PendientesViewModel", "Pendiente eliminado con ID: ${pendiente.id}")

                    // Agregar un mensaje para indicar que se eliminó el pendiente
                    Log.d("PendientesViewModel", "Se eliminó el pendiente: ${pendiente.titulo}")

                    // Agregar una línea para imprimir la lista actualizada después de eliminar el pendiente
                    Log.d("PendientesViewModel", "Lista actualizada: $_pendientesList")
                } catch (e: Exception) {
                    Log.e("PendientesViewModel", "Error al eliminar el pendiente", e)

                    // Agregar un mensaje para indicar que no se eliminó el pendiente
                    Log.d("PendientesViewModel", "No se eliminó el pendiente: ${pendiente.titulo}")
                    Log.d("PendientesViewModel", "ID del pendiente a eliminar: ${pendiente.id}")

                }
            }
        }
    }

    // Función para obtener la lista de pendientes desde Firestore y guardarla en _pendientesList
    fun obtenerListaDePendientes() {
        val db = FirebaseFirestore.getInstance()
        val pendientesCollection = db.collection("pendientes")

        pendientesCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("PendientesViewModel", "Error al obtener la lista de pendientes", error)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                _pendientesList.clear()
                for (document in snapshot.documents) {
                    val pendiente = document.toObject(Pendiente::class.java)
                    if (pendiente != null) {
                        // Asignar el ID del pendiente desde Firestore en la lista local
                        val pendienteConId = pendiente.copy(id = document.id)
                        _pendientesList.add(pendienteConId)
                    }
                }
            }
        }
    }
}