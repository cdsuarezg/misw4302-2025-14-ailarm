package com.project.ailarm.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    time: LocalTime,
    onTimeChange: (LocalTime) -> Unit,
    modifier: Modifier = Modifier,
    is24Hour: Boolean = false,
    label: String = "Hora de alarma"
) {
    var showPicker by remember { mutableStateOf(false) }

    val formatter = remember(is24Hour) {
        DateTimeFormatter.ofPattern(if (is24Hour) "HH:mm" else "hh:mm a", Locale.getDefault())
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = time.format(formatter),
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                Icon(Icons.Outlined.AccessTime, contentDescription = "Seleccionar hora")
            },
            singleLine = true,
            modifier = modifier.fillMaxWidth()
        )

        Box(
            Modifier
                .matchParentSize()
                .clickable { showPicker = true }
        )
    }

    if (showPicker) {
        TimePickerDialog(
            initialHour = time.hour,
            initialMinute = time.minute,
            is24Hour = is24Hour,
            onDismiss = { showPicker = false },
            onConfirm = { state ->
                showPicker = false
                onTimeChange(LocalTime.of(state.hour, state.minute))
            }
        )
    }
}
