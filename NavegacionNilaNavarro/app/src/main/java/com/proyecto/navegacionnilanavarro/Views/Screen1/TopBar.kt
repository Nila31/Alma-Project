package com.proyecto.navegacionnilanavarro.Views.Screen1

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, onBackPressed: () -> Unit) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Indigo200
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onBackPressed.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = title,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
        }
    }
}


