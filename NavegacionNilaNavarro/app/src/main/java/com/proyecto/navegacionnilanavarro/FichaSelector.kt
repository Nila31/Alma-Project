package com.proyecto.navegacionnilanavarro

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FichaSelector(ficha: String, onSelectFicha: (String) -> Unit) {

    Box(
        modifier = Modifier
            .size(64.dp)
            .border(1.dp, Color.Black)
            .clickable { onSelectFicha(ficha) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = ficha,
            style = TextStyle(fontSize = 24.sp),
            color = Color.Black
        )
    }
}