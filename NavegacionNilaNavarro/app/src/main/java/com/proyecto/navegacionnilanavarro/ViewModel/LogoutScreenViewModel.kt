package com.proyecto.navegacionnilanavarro.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LogoutScreenViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun signOut(callback: (success: Boolean, error: Exception?) -> Unit) {
        viewModelScope.launch {
            try {
                // Cerrar sesión del usuario
                auth.signOut()

                val currentUser = auth.currentUser
                currentUser?.uid?.let { userId ->
                    // Actualizar el campo "estadoSesion" en Firestore a false
                    db.collection("usuarios")
                        .document(userId)
                        .update("estadoSesion", false)
                        .addOnSuccessListener {
                            Log.d("Alma", "signOut: Sesión cerrada")
                            callback(true, null)
                        }
                        .addOnFailureListener { e ->
                            callback(false, e)
                        }
                }
            } catch (ex: Exception) {
                Log.d("Alma", "signOut Error: ${ex.message}")
                callback(false, ex)
            }
        }
    }
}






