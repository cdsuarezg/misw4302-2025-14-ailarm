package com.project.ailarm.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.ailarm.ui.theme.PillBg

@Composable
fun LabelChip(
    text: String,
    modifier: Modifier = Modifier,
    bgColor: Color = PillBg,
    textColor: Color = Color.Black
) {
    Surface(
        modifier = modifier,
        color = bgColor,
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = textColor,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}
