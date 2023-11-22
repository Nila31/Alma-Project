package com.proyecto.navegacionnilanavarro.Views.Login

import android.annotation.SuppressLint
import android.widget.ScrollView
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.proyecto.navegacionnilanavarro.R
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistrationScreen(navController: NavController) {
    val nameState = remember { mutableStateOf("") }
    val lastNameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    val context = LocalContext.current
    val firestoreDb = FirebaseFirestore.getInstance()

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Registro de Usuarios",
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.usuarios),
                        contentDescription = "Imagen de usuarios",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(percent = 50))
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = nameState.value,
                        onValueChange = { nameState.value = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(percent = 50),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Nombre",
                                tint = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = lastNameState.value,
                        onValueChange = { lastNameState.value = it },
                        label = { Text("Apellido") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(percent = 50),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Apellido",
                                tint = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = emailState.value,
                        onValueChange = { emailState.value = it },
                        label = { Text("Correo electrónico") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(percent = 50),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Correo electrónico",
                                tint = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = passwordState.value,
                        onValueChange = { passwordState.value = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(percent = 50),
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Contraseña",
                                tint = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val name = nameState.value
                            val lastName = lastNameState.value
                            val email = emailState.value
                            val password = passwordState.value

                            if (name.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                                // Verificar si el usuario ya existe con el mismo nombre y apellido
                                val existingUserQuery = firestoreDb.collection("usuarios")
                                    .whereEqualTo("nombre", name)
                                    .whereEqualTo("apellido", lastName)
                                    .get()

                                existingUserQuery.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val existingUsers = task.result?.documents

                                        if (existingUsers?.isEmpty() == false) {
                                            Toast.makeText(
                                                context,
                                                "Lo sentimos, el usuario ya existe",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                                                .addOnCompleteListener { task ->
                                                    if (task.isSuccessful) {
                                                        val userId = task.result?.user?.uid

                                                        val userData = hashMapOf(
                                                            "nombre" to name,
                                                            "apellido" to lastName
                                                        )

                                                        userId?.let {
                                                            firestoreDb.collection("usuarios").document(it)
                                                                .set(userData)
                                                                .addOnSuccessListener {
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Registro exitoso",
                                                                        Toast.LENGTH_SHORT
                                                                    ).show()
                                                                    navController.popBackStack()
                                                                }
                                                                .addOnFailureListener { e ->
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Error en el registro: ${e.message}",
                                                                        Toast.LENGTH_SHORT
                                                                    ).show()
                                                                }
                                                        }
                                                    } else {
                                                        Toast.makeText(
                                                            context,
                                                            "Error en el registro: ${task.exception?.message}",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                }
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Error al verificar el usuario: ${task.exception?.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Lo sentimos, debes llenar toda la información solicitada",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(percent = 50)),
                        colors = ButtonDefaults.buttonColors(Indigo200)
                    ) {
                        Text(text = "Registrarse", color = Color.White)
                    }
                }
            }
        }
    )
}