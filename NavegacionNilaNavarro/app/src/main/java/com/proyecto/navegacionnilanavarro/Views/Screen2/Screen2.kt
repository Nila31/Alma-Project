package com.proyecto.navegacionnilanavarro.Views.Screen2

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.R
import com.proyecto.navegacionnilanavarro.ViewModel.MiPantallaViewModel
import com.proyecto.navegacionnilanavarro.Views.Screen1.TopBar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListadoFundaciones(navController: NavController, viewModel: MiPantallaViewModel) {
    val nombres = viewModel.getNombres()

    Scaffold(
        topBar = { TopBar(title = "Listado de Fundaciones", onBackPressed = { navController.popBackStack() })}
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
        ) {
            itemsIndexed(nombres) { index, nombre ->
                FundacionItem(nombre = nombre, imagenIndex = index, viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FundacionItem(nombre: String, imagenIndex: Int, viewModel: MiPantallaViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        onClick = {

        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(getImagenResource(imagenIndex)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = nombre,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp)) // Espacio entre el nombre y los iconos

            val direccion = getDireccion(imagenIndex)
            val telefono = getTelefono(imagenIndex)

            if (direccion.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp), // Ajusta el espaciado aquí
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.localizacion),
                        contentDescription = "Localización",
                        modifier = Modifier.size(25.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = direccion,
                        style = MaterialTheme.typography.caption
                    )
                }
            }

            if (telefono.isNotEmpty()) {
                Spacer(modifier = Modifier.height(14.dp)) // Espacio vertical entre los iconos
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp), // Ajusta el espaciado aquí
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.vocacion),
                        contentDescription = "Vocación",
                        modifier = Modifier.size(25.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = telefono,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}

fun getDireccion(imagenIndex: Int): String {
    return when (imagenIndex) {
        0 -> "CHFH+373, David"
        1 ->  "Casa 18K, Bethania, Calle Las Bermudas, Panamá"
        2 ->  "CHCC+X6V, David"
        3 ->   "C. Guiana"
        4 ->   "2FCH+87V, Panamá"
        5->    "XFV6+C6F, Av. 22A Nte., Panamá"
        6->  "Torre Del Pacifico A, edificio 6302,, Av. Vasco Nuñez de Balboa, Panamá"
        7 ->  "9685+X77, Puerto Pilón, Sabanitas"
        8->   "C. A 87g, San Miguelito"
        9->   "XH5C+3HM, Chitré"
        10->  "Calle 5 Parque Lefevre, Casa C-29 Ciudad de Panamá"
        11->  "Av. 4C Nte. Local 1A, Panamá"
        12->  "C. 139 Este, Panamá"
        13->  "C. 144 Oeste, Panamá"
        14->  "2F4M+G7P, Panamá"

        else -> ""
    }
}

fun getTelefono(imagenIndex: Int): String {
    return when (imagenIndex) {
        0 -> "775-2614"
        1 -> "212-9012"
        3 -> "232-4893"
        4 -> "229-2020"
        6 -> "316-0460"
        10 -> "(+507) 392-9160"
        11 -> "61-4857"
        12 -> "393-9407"
        14 -> "263-9354"
        else -> ""
    }
}

fun getImagenResource(imagenIndex: Int): Int {
    // Ajusta esta función según el patrón de nombres de tus imágenes
    // Por ejemplo, si las imágenes se llaman "1.png", "2.png", etc.
    return when (imagenIndex) {
        0 -> R.drawable.santacatalina
        1 -> R.drawable.loslirios
        2 -> R.drawable.fejupechi
        3 -> R.drawable.hogarluz
        4 -> R.drawable.betania
        5 -> R.drawable.loceria
        6 -> R.drawable.nuevavida
        7 -> R.drawable.santaluisa
        8 -> R.drawable.sanjose
        9 -> R.drawable.chitre
        10 -> R.drawable.teayudamos
        11 -> R.drawable.agegolden
        12 -> R.drawable.familiafeliz
        13 -> R.drawable.sanbenito
        14-> R.drawable.loreto


        // ... Continúa con el resto de los casos ...
        else -> R.drawable.fundaciones // Imagen predeterminada si no hay una coincidencia
    }
}