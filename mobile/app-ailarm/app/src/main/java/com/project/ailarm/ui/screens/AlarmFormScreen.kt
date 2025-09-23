package com.project.ailarm.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import com.project.ailarm.ui.components.Header
import com.project.ailarm.ui.components.TimePicker
import com.project.ailarm.ui.theme.TextColor

import java.time.LocalTime

@Composable
fun AlarmFormScreen(
    onClickBackBtn: () -> Unit,
) {
    var time by remember { mutableStateOf(LocalTime.of(20, 0)) }

    Scaffold(
        topBar = {
            Header(showAccountBtn = false, showBackBtn = true, onClickBackBtn)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.TopStart
        ) {
            Column (
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Agregar una alarma",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 10.dp),
                    color = TextColor
                )

                Text(
                    text = "Completa los campos para configurar tu alarma",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 10.dp),
                    color = TextColor
                )

                TimePicker(
                    time = time,
                    onTimeChange = { time = it }
                )
            }
        }
    }
}