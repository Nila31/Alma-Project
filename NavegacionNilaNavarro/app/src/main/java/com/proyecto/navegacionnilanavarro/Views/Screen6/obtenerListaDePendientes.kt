package com.proyecto.navegacionnilanavarro.Views.Screen6

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.navegacionnilanavarro.Models.Pendiente

fun obtenerListaDePendientes(callback: (List<Pendiente>) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val pendientesList = mutableListOf<Pendiente>()

    // Realizar la consulta a Firestore y obtener la lista de pendientes
    db.collection("pendientes")
        .get()
        .addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                val pendiente = document.toObject(Pendiente::class.java)
                pendientesList.add(pendiente)
            }
            callback(pendientesList) // Llamamos al callback con la lista de pendientes obtenida
        }
        .addOnFailureListener { e ->
            Log.e("obtenerListaDePendientes", "Error al obtener la lista de pendientes", e)
            // Manejo de errores si la consulta falla
            // Aquí podrías mostrar un mensaje de error al usuario o realizar otras acciones de manejo de errores según tu necesidad.
        }
}