package com.example.language.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListItemLayout(
    icon : ImageVector,
    yoruba : String = "",
    translation : String = "",
    modifier : Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        Image(
            imageVector = icon,
            contentDescription = "List icon",
            modifier = modifier
                .width(88.dp)
                .height(88.dp)
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(88.dp)
                .padding(start = 16.dp)
        ) {
            Text(
                text = yoruba,
                modifier.weight(1f)

            )

            Text(
                text = translation,
                modifier.weight(1f)
            )

        }

    }

}

@Preview
@Composable
fun ListItemPrev() {
    ListItemLayout(
        icon = Icons.Default.Add,
        yoruba = "Ookan",
        translation = "One"
    )
}