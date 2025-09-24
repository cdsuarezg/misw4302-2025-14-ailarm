package com.project.ailarm.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp


@Composable
fun HoverIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val interaction = remember { MutableInteractionSource() }
    val hovered = interaction.collectIsHoveredAsState().value
    val pressed = interaction.collectIsPressedAsState().value
    val scale = animateFloatAsState(
        targetValue = if (hovered || pressed) 1.08f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = Spring.DampingRatioNoBouncy),
        label = "iconHoverPressScale"
    ).value

    IconButton(
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        interactionSource = interaction,
        modifier = modifier
            .hoverable(interaction)             // activa hover cuando hay cursor
            .graphicsLayer { scaleX = scale; scaleY = scale } // anima también en click/tap
    ) { content() }
}

@Composable
fun HoverFab(
    onClick: () -> Unit,
    containerColor: androidx.compose.ui.graphics.Color,
    contentColor: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    borderWidth: Dp? = null,
    borderColor: androidx.compose.ui.graphics.Color? = null,
    content: @Composable () -> Unit
) {
    val interaction = remember { MutableInteractionSource() }
    val hovered = interaction.collectIsHoveredAsState().value
    val pressed = interaction.collectIsPressedAsState().value
    val scale = animateFloatAsState(
        targetValue = if (hovered || pressed) 1.06f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = Spring.DampingRatioNoBouncy),
        label = "fabHoverPressScale"
    ).value

    FloatingActionButton(
        onClick = onClick,
        interactionSource = interaction,
        containerColor = containerColor,
        contentColor = contentColor,
        shape = shape,
        modifier = modifier
            .then(if (borderWidth != null && borderColor != null) Modifier.border(borderWidth, borderColor, shape) else Modifier)
            .hoverable(interaction)
            .graphicsLayer { scaleX = scale; scaleY = scale }
    ) { content() }
}
