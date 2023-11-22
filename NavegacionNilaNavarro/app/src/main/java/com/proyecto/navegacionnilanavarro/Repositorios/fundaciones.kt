package com.proyecto.navegacionnilanavarro.Repositorios


class NombresRepository {
    private var nombres: List<String> = emptyList()

    fun getNombres(): List<String> {
        return nombres
    }

    fun actualizarNombres(nuevosNombres: List<String>) {
        nombres = nuevosNombres
    }
}


