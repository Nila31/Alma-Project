package com.proyecto.navegacionnilanavarro

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.ViewModel.DiaryViewModel
import java.util.Calendar
import java.util.Locale


@Composable
fun WriteDiaryEntry(navController: NavController, viewModel: DiaryViewModel) {
    var isSnackbarVisible by remember { mutableStateOf(false) }
    var diaryEntryText by remember { mutableStateOf("") }

    val lightRedColor = Color(0xFFBC8F8F)
    val gaviotas = painterResource(id = R.drawable.gaviotas)
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Fondo con imagen
        Image(
            painter = gaviotas,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(1f)
                .alpha(0.4f),// Ajusta la escala según sea necesario
            contentScale = ContentScale.FillBounds
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "Escribir en el Diario",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                backgroundColor = lightRedColor,
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )

            TextField(
                value = diaryEntryText,
                onValueChange = {
                    diaryEntryText = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White),
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Solo almacena la fecha en Firestore, no el contenido
                        val formattedDate = getFormattedDate()
                        val userName = viewModel.getUserName()

                        // Guardar la entrada en Firestore
                        viewModel.saveDiaryEntryInFirestore(userName, formattedDate)

                        // Guardar la entrada localmente en la aplicación (contenido en la lista)
                        viewModel.saveDiaryEntryLocally(diaryEntryText)

                        isSnackbarVisible = true
                    }
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Solo almacena la fecha en Firestore, no el contenido
                    val formattedDate = getFormattedDate()
                    val userName = viewModel.getUserName()

                    // Guardar la entrada en Firestore
                    viewModel.saveDiaryEntryInFirestore(userName, formattedDate)

                    // Guardar la entrada localmente en la aplicación (contenido en la lista)
                    viewModel.saveDiaryEntryLocally(diaryEntryText)
                    isSnackbarVisible = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(6.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = lightRedColor)
            ) {
                Text(
                    text = "Guardar",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // SnackBar con Modifier.offset para ajustar la posición
        if (isSnackbarVisible) {
            Snackbar(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 720.dp) // Ajusta la posición verticalmente
                    .offset(x = 1.dp) // Ajusta la posición verticalmente
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Actualización guardada exitosamente",
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.width(8.dp)) // Agrega un espaciador
                    TextButton(
                        onClick = { isSnackbarVisible = false },
                    ) {
                        Text(text = "Cerrar", color = lightRedColor)
                    }
                }
            }
        }
    }
}

fun getFormattedDate(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return dateFormat.format(calendar.time)
}