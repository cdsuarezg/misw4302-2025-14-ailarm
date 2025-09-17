package com.project.ailarm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.ailarm.R
import com.project.ailarm.model.AlarmItem
import com.project.ailarm.ui.components.AlarmCard
import com.project.ailarm.ui.theme.ActionIcon
import com.project.ailarm.ui.theme.AppBarTitle
import com.project.ailarm.ui.theme.TitleGray

// Fondo general
private val ScreenBg = Color(0xFFF6F6F6)
// FAB +
private val FabAddColor = Color(0xFF9B59B6)
// Mic: icono y borde
private val MicIconColor = Color(0xFF3C3C3C)
private val MicBorderColor = Color(0xFFDBD7DF)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmListScreen(
    alarms: List<AlarmItem>,
    onAddAlarm: () -> Unit,
    onMicClick: () -> Unit,
) {
    Scaffold(
        containerColor = ScreenBg,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = ScreenBg,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ailarm_logo),
                            contentDescription = "Logo Ailarm",
                            modifier = Modifier
                                .width(98.dp)
                                .height(91.dp),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "Ailarm",
                            style = AppBarTitle,
                            color = TitleGray,
                            modifier = Modifier.offset(x = (-26).dp)
                        )
                    }
                },
                navigationIcon = {},
                actions = {
                    IconButton(
                        onClick = { /* TODO: perfil */ },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = ActionIcon,
                            disabledContentColor = ActionIcon
                        )
                    ) {
                        Icon(Icons.Outlined.AccountCircle, contentDescription = "Perfil")
                    }
                }
            )
        },
        floatingActionButton = {
            Row(
                modifier = Modifier.padding(end = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                FloatingActionButton(
                    onClick = onMicClick,
                    containerColor = ScreenBg,
                    shape = CircleShape,
                    modifier = Modifier.border(1.dp, MicBorderColor, CircleShape)
                ) {
                    Icon(Icons.Outlined.Mic, contentDescription = "Voz", tint = MicIconColor)
                }

                FloatingActionButton(
                    onClick = onAddAlarm,
                    containerColor = FabAddColor,
                    shape = CircleShape
                ) {
                    Icon(Icons.Outlined.Add, contentDescription = "Agregar", tint = Color.White)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 12.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Mis alarmas",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            alarms.forEach { alarm ->
                AlarmCard(
                    alarm = alarm,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
            }

            Spacer(Modifier.height(80.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlarmListScreenPreview() {
    val sample = listOf(
        AlarmItem("07:00 a.m.", listOf("Diaria", "Despertar")),
        AlarmItem("07:15 a.m.", listOf("Diaria", "Medicamento")),
        AlarmItem("05:00 p.m.", listOf("L, M, X, V", "Ejercicio")),
    )
    MaterialTheme {
        AlarmListScreen(
            alarms = sample,
            onAddAlarm = {},
            onMicClick = {}
        )
    }
}
