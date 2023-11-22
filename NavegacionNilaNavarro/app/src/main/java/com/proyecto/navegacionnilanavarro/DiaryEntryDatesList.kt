package com.proyecto.navegacionnilanavarro

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.Repositorios.DiaryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun DiaryEntryDatesList(navController: NavController, diaryRepository: DiaryRepository) {
    val datesLiveData = diaryRepository.getDiaryEntryDates()
    val dates = remember { mutableStateListOf<String>() }
    val dot = painterResource(id = R.drawable.dot)


    datesLiveData.observeAsState(initial = emptyList()).value?.let {
        dates.clear()
        dates.addAll(it)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Fondo con imagen
        Image(
            painter = dot,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(1f)
                .alpha(0.5f),// Ajusta la escala según sea necesario
            contentScale = ContentScale.FillBounds
        )


        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Parte superior (Top Bar)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp) // Ajusta la altura de la parte superior
                    .background(Color(0xFFBC8F8F)) // Color rojo claro
            ) {
                IconButton(
                    onClick = {
                        // Agregar acción de retroceso o navegación aquí
                        navController.popBackStack()
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atrás")
                }
                Text(
                    text = "Actualizaciones",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(dates) { date ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = date,
                            fontSize = 18.sp,
                            modifier = Modifier.clickable {
                                // Navegar a la pantalla de detalle con la fecha como argumento
                                navController.navigate("diaryEntryDetail/${date}")
                            }
                        )
                        IconButton(
                            onClick = {
                                // Utiliza GlobalScope.launch para iniciar una corrutina
                                GlobalScope.launch {
                                    // Llama a la función deleteDiaryEntry dentro de la corrutina
                                    diaryRepository.deleteDiaryEntry(date) {
                                        // Puedes agregar lógica adicional después de eliminar la entrada si es necesario

                                        // Actualiza la lista después de la eliminación
                                        dates.remove(date)
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar"
                            )

                        }
                    }
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.background(Color.LightGray)
                    )
                }
            }
        }
    }
}