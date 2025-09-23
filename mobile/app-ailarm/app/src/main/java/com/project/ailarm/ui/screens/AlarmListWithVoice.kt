package com.project.ailarm.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RecordVoiceOverlay(
    isRecording: Boolean,
    onCancel: () -> Unit,
    onStart: () -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit,
    micTint: Color,
    scrimBase: Color = Color(0xFFF6F6F6),
    scrimTargetAlpha: Float = 0.04f
) {

    var mounted by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { mounted = true }

    val scrimAlpha by animateFloatAsState(
        targetValue = if (mounted) scrimTargetAlpha else 0f,
        animationSpec = tween(180, easing = FastOutSlowInEasing),
        label = "scrimAlpha"
    )
    val scale by animateFloatAsState(
        targetValue = if (mounted) 1f else 0.98f,
        animationSpec = tween(200, easing = FastOutSlowInEasing),
        label = "cardScale"
    )
    val cardElevation by animateDpAsState(
        targetValue = if (mounted) 8.dp else 0.dp,
        animationSpec = tween(220, easing = FastOutSlowInEasing), label = "cardElev"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(scrimBase.copy(alpha = scrimAlpha))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { /* consume */ },
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color.White,
            tonalElevation = cardElevation,
            shadowElevation = cardElevation,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .graphicsLayer { scaleX = scale; scaleY = scale }
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Outlined.Mic, contentDescription = null, tint = micTint)
                Spacer(Modifier.height(8.dp))

                Crossfade(targetState = isRecording, label = "titleCrossfade") { rec ->
                    Text(
                        text = if (rec) "Escuchando..." else "Graba tu alarma",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF2C2C2C)
                    )
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Dime como quieres configurar tu alarma (hora, frecuencia, nombre y algún sonido preferido), por ejemplo: \"Quiero una alarma a las 8:00 p.m, todos los días con nombre 'descongelar pollo', con sonido alegre.\"",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF4A4A4A)
                )

                Spacer(Modifier.height(20.dp))

                Crossfade(targetState = isRecording, label = "buttonsCrossfade") { rec ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        if (rec) {
                            TextButton(onClick = onBack) { Text("Atrás") }
                            TextButton(onClick = onSave) { Text("Guardar") }
                        } else {
                            TextButton(onClick = onCancel) { Text("Cancelar") }
                            TextButton(onClick = onStart) { Text("Empezar grabación") }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DualArcLoader(
    size: Dp,
    dark: Color,
    light: Color
) {
    val transition = rememberInfiniteTransition(label = "dualArc")
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1400, easing = LinearEasing),
            RepeatMode.Restart
        ),
        label = "rotation"
    )
    val sweep1 = 250f
    val sweep2 = 190f

    Canvas(modifier = Modifier.size(size)) {
        val stroke = Stroke(width = size.toPx() * 0.08f, cap = StrokeCap.Round)
        val pad = size.toPx() * 0.12f
        val rect = androidx.compose.ui.geometry.Rect(
            left = pad, top = pad, right = size.toPx() - pad, bottom = size.toPx() - pad
        )
        drawArc(
            color = dark,
            startAngle = rotation,
            sweepAngle = sweep1,
            useCenter = false,
            style = stroke,
            topLeft = rect.topLeft,
            size = rect.size
        )
        drawArc(
            color = light,
            startAngle = 180f - rotation,
            sweepAngle = sweep2,
            useCenter = false,
            style = stroke,
            topLeft = rect.topLeft,
            size = rect.size
        )
    }
}
