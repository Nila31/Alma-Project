package com.proyecto.navegacionnilanavarro.Views.Login

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.proyecto.navegacionnilanavarro.R
import com.proyecto.navegacionnilanavarro.ViewModel.LoginScreenViewModel

import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    val loadingState = remember { mutableStateOf(false) }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // Habilita el scroll vertical
            ) {
                // Fondo con gradiente de color en la mitad superior
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.White, Color.White),
                                startY = 0f,
                                endY = 0.5f
                            )
                        )
                )

                // Imagen en la mitad superior de la pantalla
                Image(
                    painter = painterResource(id = R.drawable.ondulado),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .align(Alignment.TopCenter)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .padding(top = 100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.circular_image),
                        contentDescription = "Circular image",
                        modifier = Modifier
                            .size(250.dp)
                            .clip(CircleShape)
                            .padding(top = 3.dp)
                            .border(2.dp, Color.White, CircleShape)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    ) {
                        TextField(
                            value = emailState.value,
                            onValueChange = { newValue -> emailState.value = newValue },
                            modifier = Modifier.fillMaxSize(),
                            label = { Text(text = "Correo") },
                            singleLine = true,
                            textStyle = MaterialTheme.typography.body1,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    ) {
                        TextField(
                            value = passwordState.value,
                            onValueChange = { newValue -> passwordState.value = newValue },
                            modifier = Modifier.fillMaxSize(),
                            label = { Text(text = "Contraseña") },
                            singleLine = true,
                            textStyle = MaterialTheme.typography.body1,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            },
                            visualTransformation = PasswordVisualTransformation()
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            val email = emailState.value
                            val password = passwordState.value

                            if (email.isBlank() || password.isBlank()) {
                                Toast.makeText(
                                    context,
                                    "Debe completar la información",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }

                            loadingState.value = true

                            viewModel.signInWithEmailAndPassword(email, password) { success, error ->
                                loadingState.value = false

                                if (success) {
                                    Toast.makeText(
                                        context,
                                        "Inicio de sesión exitoso",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    CoroutineScope(Dispatchers.Main).launch {
                                        delay(3000)
                                        navController.navigate("alma") {
                                            popUpTo("home") { inclusive = true }
                                        }
                                    }
                                } else {
                                    error?.let {
                                        if (it is FirebaseAuthInvalidUserException) {
                                            Toast.makeText(
                                                context,
                                                "El usuario no existe. Inténtalo otra vez",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "El usuario o la contraseña no coinciden. Inténtalo otra vez",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }

                                    navController.navigate("home") {
                                        popUpTo("home") { inclusive = true }
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clickable { /* Manejar el evento de clic aquí */ },
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(Indigo200),
                        enabled = !loadingState.value
                    ) {
                        Text(
                            text = "Iniciar sesión",
                            style = MaterialTheme.typography.body1,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "¿No tienes una cuenta? Regístrate",
                        style = MaterialTheme.typography.body1,
                        color = Color.Black,
                        modifier = Modifier.clickable {
                            navController.navigate("registro")
                        }
                    )
                }
            }
        }
    )
}