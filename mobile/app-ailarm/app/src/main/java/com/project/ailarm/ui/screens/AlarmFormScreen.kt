package com.project.ailarm.ui.screens

import TagField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
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
import com.project.ailarm.ui.components.DropdownField

import com.project.ailarm.ui.components.Header
import com.project.ailarm.ui.components.TimePicker
import com.project.ailarm.ui.theme.SecondaryColor
import com.project.ailarm.ui.theme.TextColor

import java.time.LocalTime

@Composable
fun AlarmFormScreen(
    onClickBackBtn: () -> Unit,
    onClickSaveBtn: () -> Unit
) {
    var time by remember { mutableStateOf(LocalTime.of(20, 0)) }
    var frequency by remember { mutableStateOf("Diaria") }
    var sound by remember { mutableStateOf("Nature") }
    var tags by remember { mutableStateOf(listOf("Alarma")) }

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
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp),
                    color = TextColor
                )

                Text(
                    text = "Completa los campos para configurar tu alarma",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                    color = TextColor
                )

                Spacer(Modifier.height(20.dp))

                TimePicker(
                    time = time,
                    onTimeChange = { time = it }
                )

                Spacer(Modifier.height(20.dp))

                DropdownField(
                    value = frequency,
                    onSelect = { frequency = it },
                    label = "Frecuencia",
                    options = listOf("Diaria", "Semanal", "Mensual", "Personalizado")
                )

                Spacer(Modifier.height(20.dp))

                TagField(
                    tags = tags,
                    onTagsChange = { tags = it },
                    label = "Etiqueta",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(20.dp))

                DropdownField(
                    value = sound,
                    onSelect = { sound = it },
                    label = "Sonido",
                    options = listOf("Nature", "Rainbow", "Tech", "Water")
                )

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = { onClickBackBtn() },
                        modifier = Modifier.height(50.dp).width(110.dp)
                    ) {
                        Text("Cancelar")
                    }

                    Spacer(Modifier.width(8.dp))

                    Button(
                        onClick = { onClickSaveBtn() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SecondaryColor,
                            contentColor = TextColor
                        ),
                        modifier = Modifier.height(50.dp).width(110.dp)
                    ) {
                        Text("Guardar")
                    }
                }
              }
            }
        }
}