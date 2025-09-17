package com.project.ailarm.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.ailarm.model.AlarmItem
import androidx.compose.material3.IconButtonDefaults
import com.project.ailarm.ui.theme.ActionIcon

@Composable
fun AlarmCard(
    alarm: AlarmItem,
    modifier: Modifier = Modifier,
    // Callbacks opcionales. Si son null, los botones se muestran deshabilitados.
    onEdit: (() -> Unit)? = null,
    onDelete: (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp,
        shadowElevation = 1.dp
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

                // Edit
                IconButton(
                    onClick = { onEdit?.invoke() ?: run {} },
                    enabled = onEdit != null,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = ActionIcon,
                        disabledContentColor = ActionIcon
                    )
                ) {
                    Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Editar")
                }

                // Delete
                IconButton(
                    onClick = { onDelete?.invoke() ?: run {} },
                    enabled = onDelete != null,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = ActionIcon,
                        disabledContentColor = ActionIcon
                    )
                ) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Eliminar")
                }
            }

            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                alarm.tags.forEach { LabelChip(text = it) }
            }
        }
    }
}
