package com.example.language.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.language.ui.theme.Dimens
import com.google.accompanist.pager.*


data class TabItems(
    val value : String,
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LanguageScreen() {

    val pagerState = rememberPagerState()
    val tabsTitles =
        remember {
            listOf(
                TabItems("Numbers"), TabItems("Family"),
                TabItems("Colors"), TabItems("Phrases")
            )
        }

    Pager(
        pagerState = pagerState,
        tabsTitles = tabsTitles,
        scope = rememberCoroutineScope()
    ) {
        HorizontalPager(
            count = tabsTitles.size,
            state = pagerState,
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) { page ->
            when (page) {
                //image
                0 -> {
                    Text(text = "$page")
                }
                //video
                1 -> {
                    Text(text = "$page")
                }
            }
        }

    }

}



