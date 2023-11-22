package com.proyecto.navegacionnilanavarro.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        callback: (success: Boolean, error: Exception?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val currentUser = auth.currentUser
                            currentUser?.uid?.let { userId ->
                                // Actualizar el campo "estadoSesion" en Firestore a true
                                db.collection("usuarios")
                                    .document(userId)
                                    .update("estadoSesion", true)
                                    .addOnSuccessListener {
                                        Log.d("Alma", "signInWithEmailAndPassword: Logueado")
                                        callback(true, null)
                                    }
                                    .addOnFailureListener { e ->
                                        callback(false, e)
                                    }
                            }
                        } else {
                            val exception = task.exception as? FirebaseAuthException
                            Log.d("Alma", "signInWithEmailAndPassword Error: ${exception?.message}")
                            callback(false, exception)
                        }
                    }
            } catch (ex: Exception) {
                Log.d("Alma", "signInWithEmailAndPassword Error: ${ex.message}")
                callback(false, ex)
            }
        }
    }
}