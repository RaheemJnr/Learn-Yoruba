package com.example.language.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.language.ui.theme.Dimens

@Composable
fun PageHeader(
    modifier : Modifier = Modifier,
    title : String = "Yoruba Language",
    titleTextStyle : TextStyle = MaterialTheme.typography.headlineMedium,
    contentColor : Color = MaterialTheme.colorScheme.onBackground,
    extraItems : @Composable RowScope.() -> Unit = {}
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        contentColor = contentColor,
        shadowElevation = 2.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.MediumPadding.size),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = Dimens.SmallPadding.size),
                text = title,
                style = titleTextStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = contentColor.copy(alpha = 0.8f),
                textAlign = TextAlign.Start
            )
            extraItems()
        }
    }
}