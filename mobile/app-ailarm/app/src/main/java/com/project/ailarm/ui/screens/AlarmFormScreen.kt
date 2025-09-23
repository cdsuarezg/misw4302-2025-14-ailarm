package com.project.ailarm.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.ailarm.ui.components.Header

@Composable
fun AlarmFormScreen() {
    Scaffold(
        topBar = {
            Header()
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Aquí irá el formulario de la alarma",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}