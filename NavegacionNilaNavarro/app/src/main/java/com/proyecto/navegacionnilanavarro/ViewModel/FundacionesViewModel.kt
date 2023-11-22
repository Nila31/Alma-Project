package com.proyecto.navegacionnilanavarro.ViewModel

import androidx.lifecycle.ViewModel
import com.proyecto.navegacionnilanavarro.Repositorios.NombresRepository

class MiPantallaViewModel : ViewModel() {
    private val nombresRepository = NombresRepository()

    init {
        val nuevosNombres = listOf(
            "Hogar de Ancianos Santa Catalina",
            "Hogar los Lirios",
            "Fejupechi  Casa del Jubilado Y Pensionado Chiriquí",
            "Hogar Luz y Vida, Paraíso",
            "Asociación Edad III de Betania",
            "Asociación Renovemos Nuestras Vidas de La Tercera Edad de La  Locería",
            "Fundación Nueva Vida",
            "Hogar Santa Luisa",
            "Hogar San José",
            "Casa de los Jubilados y Pensionados de Chitre",
            "Hogar te ayudamos",
            "Golden Age Panamá",
            "Residencial Geriatrico Familia Feliz",
            "Hogar de Ancianos San Benito",
            "Hogar Virgen de Loreto - Geriatrico"




        )
        nombresRepository.actualizarNombres(nuevosNombres)
    }

    fun getNombres(): List<String> {
        return nombresRepository.getNombres()
    }
}























