package com.proyecto.navegacionnilanavarro.Views.Screen9

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.proyecto.navegacionnilanavarro.R
import com.proyecto.navegacionnilanavarro.Views.Screen8.CardClickable
import com.proyecto.navegacionnilanavarro.Views.Screen8.ColoredCard

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AlimentacionBalanceadaScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF9FA8DA), // Color Indigo 200
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back",
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Text(
                        text = "Alimentación Balanceada",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            val images = listOf(
                R.drawable.pollo,
                R.drawable.vegetales,
                R.drawable.cereales,
                R.drawable.aceite,
                R.drawable.agua
            )

            val titles = listOf(
                "Ingesta de Proteínas Magras",
                "Frutas y Verduras Variadas",
                "Fuentes de Fibra",
                "Grasas Saludables",
                "Hidratación Adecuada"
            )

            for (index in images.indices) {
                val color = Color.White
                val title = titles[index]

                CardClickable(
                    onClick = {
                        when (title) {
                            "Ingesta de Proteínas Magras" -> navController.navigate("ProteinasScreen")
                            "Frutas y Verduras Variadas" -> navController.navigate("FrutasYVerdurasScreen")
                            "Fuentes de Fibra" -> navController.navigate("FuentesDeFibraScreen")
                            "Grasas Saludables" -> navController.navigate("GrasasSaludablesScreen")
                            "Hidratación Adecuada" -> navController.navigate("HidratacionAdecuadaScreen")
                        }
                    },
                    content = {
                        ColoredCard(color = color, imageRes = images[index], title = title)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ColoredCard(color: Color, imageRes: Int, title: String) {
    Card(
        backgroundColor = color,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(110.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                fontSize = 20.sp, // Establecer el tamaño de fuente aquí
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterVertically) // Centrar verticalmente el texto
                    .padding(start = 2.dp) // Agregar espaciado a la derecha
            )
        }
    }
}

@Composable
fun CardClickable(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.clickable {
            onClick()
        },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}