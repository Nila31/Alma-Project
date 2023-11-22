package com.proyecto.navegacionnilanavarro.Repositorios

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class DiaryRepository {
    private val db = FirebaseFirestore.getInstance()
    private val diaryCollection = db.collection("diario")
    private val usersCollection = db.collection("usuarios")

    fun getDiaryEntryDates(): LiveData<List<String>> {
        val datesLiveData = MutableLiveData<List<String>>()

        val query = diaryCollection.orderBy("creationDate", Query.Direction.DESCENDING)

        val listener = EventListener<QuerySnapshot> { querySnapshot, error ->
            if (error != null) {
                // Manejar el error según sea necesario
                return@EventListener
            }

            val dates = querySnapshot?.documents?.mapNotNull { it.getString("creationDate") }
            if (dates != null) {
                datesLiveData.value = dates
            } else {
                datesLiveData.value = emptyList()
            }
        }

        val registration = query.addSnapshotListener(listener)

        // Eliminar el listener cuando el LiveData sea eliminado
        datesLiveData.observeForever {
            registration.remove()
        }

        return datesLiveData
    }

    suspend fun saveDiaryEntry(userName: String, creationDate: String, content: String) {
        try {
            val newEntry = hashMapOf(
                "userName" to userName,
                "creationDate" to creationDate,
                "content" to content
            )
            diaryCollection.add(newEntry).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Función para obtener el nombre de usuario desde la colección "usuarios"
    suspend fun getUserNameFromUsersCollection(): String {
        val userDocument = usersCollection.document("documento_del_usuario").get().await()
        return userDocument.getString("nombre") ?: ""
    }

    suspend fun deleteDiaryEntry(date: String, onComplete: () -> Unit) {
        try {
            val entryToDelete = diaryCollection.whereEqualTo("creationDate", date)
            entryToDelete.get().addOnSuccessListener { documents ->
                for (document in documents) {
                    diaryCollection.document(document.id).delete()
                }
                onComplete.invoke()
            }.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}