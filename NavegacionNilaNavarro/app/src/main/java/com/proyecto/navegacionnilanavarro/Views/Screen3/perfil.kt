package com.proyecto.navegacionnilanavarro.Views.Screen3

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.proyecto.navegacionnilanavarro.R
import com.proyecto.navegacionnilanavarro.ViewModel.UserProfileData
import com.proyecto.navegacionnilanavarro.ViewModel.UserViewModel
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileDetailsScreen(navController: NavController, viewModel: UserViewModel) {
    val userProfileState: UserProfileData? by viewModel.userProfile.collectAsState()
    val imagePickerDialogOpen = remember { mutableStateOf(false) }
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }
    val isImageUploading = remember { mutableStateOf(false) }

    // Cargar el perfil de usuario al inicio
    LaunchedEffect(Unit) {
        viewModel.loadUserProfile()
    }

    Scaffold(
        topBar = {
            // TopAppBar y otras configuraciones
        }
    ) {
        userProfileState?.let { profile ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Icono circular que representa al usuario o icono predeterminado
                val imageUrl = profile.imageUrl
                val defaultIcon = painterResource(id = R.drawable.usuarios)
                val painter =
                    if (!imageUrl.isNullOrEmpty()) rememberImagePainter(data = imageUrl) else defaultIcon

                // Mostrar animación circular mientras se carga la imagen
                if (isImageUploading.value) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .offset(y = (-100).dp)
                    )
                } else {
                    // Imprime la URL de la imagen antes de pasarla a rememberImagePainter
                    Log.d("ImageLoad", "Image URL: ${profile.imageUrl}")

                    // Agrega esta línea para imprimir el valor de la variable painter
                    Log.d("ImageLoad", "Painter: $painter")

                    Image(
                        painter = painterResource(id = R.drawable.usuarios), // Icono predeterminado
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(250.dp)
                            .offset(y = (-100).dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Mensaje de bienvenida con el nombre y apellido del usuario
                Text(
                    text = "¡Hola, ${profile.nombre} ${profile.apellido}!",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .offset(y = (-81).dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Rectángulos a la derecha
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-50).dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Rectángulo "Cambiar"
                    Box(
                        modifier = Modifier
                            .clickable { imagePickerDialogOpen.value = true }
                            .size(170.dp, 60.dp)
                            .background(Color.White)
                            .border(
                                2.dp,
                                Indigo200,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(16.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.editar),
                                contentDescription = null,
                                modifier = Modifier,
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(25.dp))
                            Text(text = "Cambiar", color = Color.Black)
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Rectángulo "Retirar"
                    Box(
                        modifier = Modifier
                            .clickable { /* Acción al hacer clic en el rectángulo Retirar */ }
                            .size(170.dp, 60.dp)
                            .background(Color.White)
                            .border(
                                2.dp,
                                Indigo200,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(16.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.borrar),
                                contentDescription = null,
                                modifier = Modifier,
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(25.dp))
                            Text(text = "Retirar", color = Color.Black)
                        }
                    }
                }

                // Llamada a la función ImagePicker
                if (imagePickerDialogOpen.value) {
                    ImagePicker(
                        onImageSelected = { uri ->
                            selectedImageUri.value = uri // Actualizar la URI de la imagen seleccionada
                            isImageUploading.value = true // Iniciar animación circular
                            imagePickerDialogOpen.value = false // Cerrar el diálogo

                            if (selectedImageUri.value != null) {
                                Log.d("ImagePicker", "Selected image URI: ${selectedImageUri.value}")
                                // Llama a la función en el ViewModel para subir la imagen a Firebase Storage
                                viewModel.uploadImageToStorage(
                                    selectedImageUri.value!!,
                                    profile.nombre
                                )

                                // Log para indicar que la imagen de perfil se está actualizando
                                Log.d("ImagePicker", "Updating profile image...")

                                // Actualizar el estado de la animación cuando la imagen se haya actualizado
                                viewModel.loadUserProfile()

                                // Agregar esta línea para imprimir la URL de la imagen después de actualizar el perfil
                                val updatedUserProfile = userProfileState
                                Log.d("ImageLoad", "Updated profile image URL: ${updatedUserProfile?.imageUrl}")

                                // Agrega esta línea para indicar que la imagen se ha actualizado y está lista para mostrar
                                Log.d("ImageLoad", "Profile image updated and ready to display.")
                            } else {
                                Log.d("ImagePicker", "No image selected.")
                                isImageUploading.value = false // Detener animación circular
                            }
                        }
                    )
                }
            }
        }
    }
}