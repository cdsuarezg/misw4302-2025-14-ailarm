package com.project.ailarm.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.ailarm.model.AlarmItem
import com.project.ailarm.ui.theme.PrimaryColor
import com.project.ailarm.ui.theme.White

@Composable
fun AlarmCard(
    alarm: AlarmItem,
    modifier: Modifier = Modifier,
    onEdit: (() -> Unit)? = null,
    onDelete: (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = White,
        tonalElevation = 1.dp,
        border = BorderStroke(1.dp, Color(0xFFCAC4D0))
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = alarm.timeText,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))

                val iconColors = IconButtonDefaults.iconButtonColors(
                    contentColor = PrimaryColor,
                    disabledContentColor = PrimaryColor
                )

                HoverIconButton(
                    onClick = { onEdit?.invoke() ?: Unit },
                    colors = iconColors,
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Editar",
                        modifier = Modifier.width(30.dp).height(30.dp)
                    )
                }

                HoverIconButton(
                    onClick = { onDelete?.invoke() ?: Unit },
                    colors = iconColors,
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Eliminar",
                        modifier = Modifier.width(30.dp).height(30.dp)
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                alarm.tags.forEach { LabelChip(text = it) }
            }
        }
    }
}
