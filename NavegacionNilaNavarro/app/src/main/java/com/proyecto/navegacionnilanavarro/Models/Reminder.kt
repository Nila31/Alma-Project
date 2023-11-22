package com.proyecto.navegacionnilanavarro.Models

data class Reminder(
    val medicationName: String,
    val dosage: String,
    val timeFrequency: String?,
    val timeInMinutes: Int,
    val isAmPmSelected: Boolean,
    val userId: String
)