package com.proyecto.navegacionnilanavarro.ViewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.navegacionnilanavarro.Models.DiaryEntry
import com.proyecto.navegacionnilanavarro.Repositorios.DiaryRepository
import com.proyecto.navegacionnilanavarro.getFormattedDate
import kotlinx.coroutines.launch

class DiaryViewModel : ViewModel() {
    private val diaryRepository = DiaryRepository()

    private var userName: String = "" // Inicialmente vacío

    // Utiliza LiveData para la lista de entradas del diario
    private val _diaryEntries = MutableLiveData<List<DiaryEntry>>()
    val diaryEntries: LiveData<List<DiaryEntry>> get() = _diaryEntries

    fun getUserName() = userName

    fun saveDiaryEntryLocally(content: String) {
        val formattedDate = getFormattedDate()
        val userName = getUserName()

        // Obtén la lista actual de entradas y agrega la nueva entrada
        val currentEntries = _diaryEntries.value.orEmpty()
        val entry = DiaryEntry(userName, formattedDate, content)  // Asegúrate de pasar el contenido correcto aquí
        _diaryEntries.value = currentEntries + listOf(entry)
    }

    fun saveDiaryEntryInFirestore(userName: String, creationDate: String) {
        viewModelScope.launch {
            diaryRepository.saveDiaryEntry(userName, creationDate, "")
        }
    }

    fun getDiaryEntryTextForDate(date: String): String? {
        return _diaryEntries.value?.find { it.creationDate == date }?.content
    }


    fun deleteDiaryEntry(date: String) {
        // Lógica para eliminar la entrada en tu repositorio
    }
}

