import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.ailarm.ui.theme.SecondaryColor
import com.project.ailarm.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TagField(
    tags: List<String>,
    onTagsChange: (List<String>) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Etiqueta"
) {
    var text by remember { mutableStateOf("") }

    fun commitTokens(raw: String) {
        val tokens = raw.split(" ", ",", ";")
            .map { it.trim() }
            .filter { it.isNotEmpty() }

        if (tokens.isEmpty()) return
        val base = tags.toMutableList()
        tokens.forEach { t ->
            if (!base.contains(t)) {
                base.add(t)
            }
        }
        onTagsChange(base)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = text,
            onValueChange = { new ->
                if (new.lastOrNull() in listOf(' ', ',', ';')) {
                    commitTokens(new)
                    text = ""
                } else {
                    text = new
                }
            },
            label = { Text(label) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tags.forEach { tag ->
                AssistChip(
                    onClick = { },
                    label = { Text(tag) },
                    trailingIcon = {
                        Icon(
                            Icons.Outlined.Close,
                            contentDescription = "Eliminar $tag",
                            modifier = Modifier.width(20.dp).height(20.dp).clickable {
                                onTagsChange(tags - tag)
                            }
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = SecondaryColor,
                        labelColor = TextColor
                    ),
                    border = BorderStroke(1.dp, SecondaryColor)
                )
            }
        }
    }
}