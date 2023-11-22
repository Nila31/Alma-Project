package com.proyecto.navegacionnilanavarro.ViewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.analytics.UserProfile
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserProfileData(
    var nombre: String = "",
    var apellido: String = "",
    var imageUrl: String = "" ,
    var estadoAnimo: String = ""// Agregamos imageUrl para almacenar la URL de la imagen de perfil
)

class UserViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance("gs://alma-391216.appspot.com")

    private val _userProfile = MutableStateFlow<UserProfileData?>(null) // Usamos MutableStateFlow
    val userProfile: StateFlow<UserProfileData?> = _userProfile

    // Agrega la propiedad isImageUploading para controlar el estado de carga de la imagen
    private val _isImageUploading = MutableStateFlow(false)
    val isImageUploading: StateFlow<Boolean> = _isImageUploading


    fun loadUserProfile() {
        val currentUser = auth.currentUser

        currentUser?.uid?.let { userId ->
            db.collection("usuarios")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val nombre = document.getString("nombre")
                        val apellido = document.getString("apellido")
                        val imageUrl = document.getString("imageUrl")

                        if (!nombre.isNullOrEmpty() && !apellido.isNullOrEmpty()) {
                            val estadoAnimo = document.getString("estadoAnimo") ?: ""
                            val userProfile = UserProfileData(
                                nombre = nombre,
                                apellido = apellido,
                                imageUrl = imageUrl ?: "",
                                estadoAnimo = estadoAnimo

                            )
                            _userProfile.value = userProfile

                            // Agregar un registro de consola para verificar el estado del perfil después de la carga
                            Log.d("UserProfile", "Profile loaded: $userProfile")
                        }
                    }
                }
        }
    }

    private fun updateImageUrlInFirestore(userId: String, imageUrl: String) {
        val userRef = db.collection("usuarios").document(userId)

        userRef.update("imageUrl", imageUrl)
            .addOnSuccessListener {
                Log.d("ImageUpload", "Image URL updated in Firestore")
                // Realiza cualquier otra acción necesaria después de actualizar la URL en Firestore
            }
            .addOnFailureListener { exception ->
                Log.e("ImageUpload", "Failed to update image URL in Firestore: ${exception.message}")
                // Manejar el error de acuerdo a tus necesidades
            }
    }

    fun uploadImageToStorage(imageUri: Uri, userName: String) {
        Log.d("ImageUpload", "Image URI: $imageUri")

        Log.d("ImageUpload", "Before upload - isImageUploading: ${_isImageUploading.value}")

        if (imageUri != null) {
            Log.d("ImageUpload", "Uploading image to Firebase Storage...")

            val fileName = "$userName-${imageUri.lastPathSegment}"
            val storageRef = storage.reference.child("images/$fileName")
            val uploadTask = storageRef.putFile(imageUri)

            _isImageUploading.value = true // Iniciar animación circular
            Log.d("ImageUpload", "isImageUploading set to true (upload started)")

            uploadTask.addOnFailureListener { exception ->
                Log.e("ImageUpload", "Upload failed: ${exception.message}")
                _isImageUploading.value = false // Detener animación circular
                Log.d("ImageUpload", "isImageUploading set to false (failure)")
                // Manejar la falla de la carga
            }.addOnSuccessListener { taskSnapshot ->
                val downloadUrl = taskSnapshot.metadata?.reference?.downloadUrl.toString() // Obtener la URL de descarga
                Log.d("ImageUpload", "Image uploaded successfully. Download URL: $downloadUrl")

                // Actualizar la URL de la imagen en el perfil del usuario
                if (!downloadUrl.isNullOrEmpty()) {
                    val updatedUserProfile = userProfile.value?.copy(imageUrl = downloadUrl)
                    _userProfile.value = updatedUserProfile
                    _isImageUploading.value = false // Detener animación circular
                    Log.d("ImageUpload", "isImageUploading set to false (success)")

                    // Actualizar la URL en Firestore con la URL de descarga real
                    val currentUser = auth.currentUser
                    val userId = currentUser?.uid
                    if (userId != null) {
                        updateImageUrlInFirestore(userId, downloadUrl) // Actualizar la URL en Firestore
                    }

                    // Agrega este log para verificar la URL actualizada en el perfil
                    Log.d("ImageUpload", "Updated image URL in profile: ${updatedUserProfile?.imageUrl}")

                    // Agrega este log para imprimir la URL de descarga de la imagen
                    Log.d("ImageUpload", "Download URL of the uploaded image: $downloadUrl")
                }
            }
        } else {
            Log.d("ImageUpload", "Image is null. Not uploading to Firebase Storage.")
        }
    }

    fun updateEstadoAnimo(userId: String, estadoAnimo: String) {
        val userRef = db.collection("usuarios").document(userId)

        userRef.update("estadoAnimo", estadoAnimo)
            .addOnSuccessListener {
                Log.d("EstadoAnimoUpdate", "Estado de ánimo actualizado en Firestore: $estadoAnimo")
                // Realiza cualquier otra acción necesaria después de actualizar el estado de ánimo
            }
            .addOnFailureListener { exception ->
                Log.e("EstadoAnimoUpdate", "Error al actualizar el estado de ánimo en Firestore: ${exception.message}")
                // Manejar el error de acuerdo a tus necesidades
            }
    }
    fun cerrarSesion() {
        // Lógica para cerrar sesión en FirebaseAuth
        FirebaseAuth.getInstance().signOut()

        // Actualizar el estado de sesión en Firestore
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        userId?.let {
            val userRef = FirebaseFirestore.getInstance().collection("usuarios").document(userId)
            userRef.update("estasession", false)
                .addOnSuccessListener {
                    // Éxito al actualizar el estado de sesión en Firestore
                    // Realizar cualquier otra acción necesaria después de cerrar sesión
                }
                .addOnFailureListener { e ->
                    // Error al actualizar el estado de sesión en Firestore
                    // Manejar el error de acuerdo a tus necesidades
                }
        }

    }
}






