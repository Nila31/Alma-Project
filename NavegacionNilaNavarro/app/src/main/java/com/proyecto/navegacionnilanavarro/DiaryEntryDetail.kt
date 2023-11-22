package com.proyecto.navegacionnilanavarro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.ViewModel.DiaryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryEntryDetail(
    navController: NavController,
    date: String,
    viewModel: DiaryViewModel
) {
    val diaryEntryText = viewModel.getDiaryEntryTextForDate(date)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Detalles de la Entrada de Diario",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atrás")
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Si se encuentra el contenido de la entrada para la fecha seleccionada, muéstralo.
            if (diaryEntryText != null) {
                Text(
                    text = diaryEntryText,
                    fontSize = 18.sp,
                )
            } else {
                // Si no se encuentra el contenido, muestra un mensaje indicando que no hay entrada para esta fecha.
                Text(
                    text = "No se encontró contenido para esta fecha",
                    fontSize = 18.sp,
                    color = Color.Red // Puedes ajustar el color según tus preferencias
                )
            }
        }
    }
}