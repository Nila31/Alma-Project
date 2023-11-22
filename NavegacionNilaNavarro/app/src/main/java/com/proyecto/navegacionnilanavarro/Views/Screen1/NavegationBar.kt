package com.proyecto.navegacionnilanavarro.Views.Screen1
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.navegacionnilanavarro.Routes
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200

@Composable
fun NavigationBar(
    navController: NavController,
    isUserLoggedIn: MutableState<Boolean>
) {
    val expandedPerfil = remember { mutableStateOf(false) }
    val expandedAjustes = remember { mutableStateOf(false) }
    val context = LocalContext.current

    BottomNavigation(backgroundColor = Indigo200) {
        BottomNavigationItem(
            selected = navController.currentDestination?.route == "home",
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") }
        )

        BottomNavigationItem(
            selected = navController.currentDestination?.route == "perfil",
            onClick = { expandedPerfil.value = !expandedPerfil.value },
            icon = {
                IconButton(onClick = { expandedPerfil.value = true }) {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .size(48.dp)
                            .padding(top = 24.dp) // Ajusta el valor del padding según tus preferencias
                    )
                }
            },
            label = { Text("Perfil") },
            alwaysShowLabel = true
        )

        if (expandedPerfil.value) {
            DropdownMenu(
                expanded = expandedPerfil.value,
                onDismissRequest = { expandedPerfil.value = false },
                modifier = Modifier.navigationBarsPadding()
            ) {
                DropdownMenuItem(onClick = {
                    // Lógica para cerrar sesión
                    isUserLoggedIn.value = false
                    Toast.makeText(
                        context,
                        "Cierre de sesión exitoso",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Actualizar el estado de sesión en Firestore
                    val userId = FirebaseAuth.getInstance().currentUser?.uid
                    if (userId != null) {
                        val userRef = FirebaseFirestore.getInstance().collection("usuarios").document(userId)
                        userRef.update("estadoSesion", false)
                            .addOnSuccessListener {
                                Log.d("Firestore", "Estado de sesión actualizado en Firestore")
                            }
                            .addOnFailureListener { e ->
                                Log.e("Firestore", "Error al actualizar el estado de sesión en Firestore", e)
                            }
                    }

                    navController.navigate("home")
                    expandedPerfil.value = false
                }) {
                    Text(text = "Cerrar sesión")
                }

            }
        }

        BottomNavigationItem(
            selected = navController.currentDestination?.route == "ajustes",
            onClick = { expandedAjustes.value = !expandedAjustes.value },
            icon = { Icon(Icons.Default.Settings, contentDescription = "Ajustes") },
            label = { Text("Ajustes") }
        )

        if (expandedAjustes.value) {
            DropdownMenu(
                expanded = expandedAjustes.value,
                onDismissRequest = { expandedAjustes.value = false },
                modifier = Modifier.navigationBarsPadding()
            ) {
                DropdownMenuItem(onClick = {
                    // Navegar a la pantalla de política de seguridad
                    navController.navigate("politica")
                    expandedAjustes.value = false
                }) {
                    Text(text = "Política de Seguridad")
                }
            }
        }

        BottomNavigationItem(
            selected = navController.currentDestination?.route == Routes.MenuScreen,
            onClick = { navController.navigate(Routes.ListadoFundaciones) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Comunidad") },
            label = { Text("Comunidad") }
        )
    }
}

