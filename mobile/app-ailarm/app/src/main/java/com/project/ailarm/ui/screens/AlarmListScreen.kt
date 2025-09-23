package com.project.ailarm.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.ailarm.R
import com.project.ailarm.model.AlarmItem
import com.project.ailarm.ui.components.AlarmCard
import com.project.ailarm.ui.components.HoverFab
import com.project.ailarm.ui.components.HoverIconButton
import com.project.ailarm.ui.theme.ActionIcon
import com.project.ailarm.ui.theme.AppBarTitle
import com.project.ailarm.ui.theme.TitleGray
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.project.ailarm.ui.components.Header

private val ScreenBg = Color(0xFFF6F6F6)
private val FabAddColor = Color(0xFF9C59B6)
private val MicIconColor = Color(0xFF3C3C3C)
private val MicBorderColor = Color(0xFFDBD7DF)

private enum class VoiceDialogState { Closed, Guide, Recording }

@Composable
fun AlarmListScreen(
    alarms: List<AlarmItem>,
    onAddAlarm: () -> Unit
) {
    val items = remember { mutableStateListOf<AlarmItem>().also { it.addAll(alarms) } }

    var dialogState by remember { mutableStateOf(VoiceDialogState.Closed) }
    var saving by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val blurAnim by animateDpAsState(
        targetValue = if (saving) 6.dp else 0.dp,
        animationSpec = tween(180, easing = FastOutSlowInEasing),
        label = "contentBlur"
    )
    val savingScrimAlpha by animateFloatAsState(
        targetValue = if (saving) 0.06f else 0f,
        animationSpec = tween(180, easing = FastOutSlowInEasing),
        label = "savingScrim"
    )

    suspend fun saveAlarm() {
        dialogState = VoiceDialogState.Closed
        saving = true
        delay(1600)
        items.add(AlarmItem("08:00 p.m.", listOf("Diaria", "Descongelar el pollo")))
        saving = false
        snackbarHostState.showSnackbar(
            message = "Tu alarma para las 08:00 p.m. está lista",
            withDismissAction = true
        )
    }

    Scaffold(
        containerColor = ScreenBg,
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = FabAddColor,
                    contentColor = Color.White,
                    actionColor = Color.White
                )
            }
        },
        topBar = {
            Header(showAccountBtn = true, onClickBackBtn = {})
        },
        floatingActionButton = {
            Row(
                modifier = Modifier.padding(end = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HoverFab(
                    onClick = {
                        dialogState = VoiceDialogState.Guide
                    },
                    containerColor = Color.White,
                    contentColor = MicIconColor,
                    borderWidth = 1.dp,
                    borderColor = MicBorderColor
                ) {
                    Icon(Icons.Outlined.Mic, contentDescription = "Voz")
                }
                HoverFab(
                    onClick = {
                        onAddAlarm()
                    },
                    containerColor = FabAddColor,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Outlined.Add, contentDescription = "Agregar")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 12.dp)
                    .fillMaxSize()
                    .blur(blurAnim)
                    .then(if (saving) Modifier.blur(6.dp) else Modifier)
            ) {
                Text(
                    text = "Mis alarmas",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                items.forEach { alarm ->
                    AlarmCard(
                        alarm = alarm,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )
                }

                Spacer(Modifier.height(80.dp))
            }


            if (dialogState != VoiceDialogState.Closed && !saving) {
                RecordVoiceOverlay(
                    isRecording = dialogState == VoiceDialogState.Recording,
                    onCancel = { dialogState = VoiceDialogState.Closed },
                    onStart  = { dialogState = VoiceDialogState.Recording },
                    onBack   = { dialogState = VoiceDialogState.Guide },
                    onSave   = { scope.launch { saveAlarm() } },
                    micTint  = FabAddColor,
                    scrimBase = ScreenBg,
                    scrimTargetAlpha = 0.04f
                )
            }


            if (saving || savingScrimAlpha > 0f) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(ScreenBg.copy(alpha = savingScrimAlpha))
                    )
                    if (saving) {
                        DualArcLoader(size = 120.dp, dark = ActionIcon, light = Color(0xFFB1AAFF))
                    }
                }
            }
        }
    }
}
