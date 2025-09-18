package com.project.ailarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.project.ailarm.model.AlarmItem
import com.project.ailarm.ui.screens.AlarmListScreen
import com.project.ailarm.ui.theme.AilarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AilarmTheme {
                val sample = listOf(
                    AlarmItem("07:00 a.m.", listOf("Diaria", "Despertar")),
                    AlarmItem("07:15 a.m.", listOf("Diaria", "Medicamento")),
                    AlarmItem("05:00 p.m.", listOf("L, M, X, V", "Ejercicio")),
                )
                AlarmListScreen(
                    alarms = sample,
                    onAddAlarm = { /* TODO */ },
                    onMicClick = { /* TODO */ }
                )
            }
        }
    }
}
