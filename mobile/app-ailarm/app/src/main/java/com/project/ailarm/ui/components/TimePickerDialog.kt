package com.project.ailarm.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.project.ailarm.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    initialHour: Int,
    initialMinute: Int,
    is24Hour: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (state: TimePickerState) -> Unit,
) {
    val state = rememberTimePickerState(initialHour, initialMinute, is24Hour)
    var inputMode by remember { mutableStateOf(true) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = MaterialTheme.shapes.extraLarge) {
            Column(Modifier.padding(24.dp)) {
                Text("Escoge la hora", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(16.dp))

                if (inputMode) {
                    TimeInput(state = state)
                } else {
                    TimePicker(state = state)
                }

                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { inputMode = !inputMode }) {
                       if (inputMode) {
                           Icon(Icons.Outlined.AccessTime, contentDescription = "Mostrar reloj", tint = PrimaryColor)
                       } else {
                           Icon(Icons.Outlined.Keyboard, contentDescription = "Mostrar teclado", tint = PrimaryColor)
                       }
                    }

                    Row {
                        TextButton(onClick = onDismiss) { Text("Cancelar", color = PrimaryColor) }
                        Spacer(Modifier.width(8.dp))
                        TextButton(onClick = { onConfirm(state) }) { Text("OK", color = PrimaryColor) }
                    }
                }
            }
        }
    }
}
