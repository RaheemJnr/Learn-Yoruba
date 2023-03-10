package com.example.language.ui.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@Composable
fun CustomTabIndicator(
    currentTabPosition: TabPosition,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .zIndex(1f)
            .fillMaxSize()
            .customTabIndicatorOffset(currentTabPosition)
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                shape = CircleShape
            )
    )
}

fun Modifier.customTabIndicatorOffset(currentTabPosition: TabPosition): Modifier =
    composed {
        val currentTabWidth = currentTabPosition.width
        val indicatorOffset by animateDpAsState(
            targetValue = currentTabPosition.left,
            animationSpec = tween()
        )
        fillMaxSize()
            .wrapContentSize(Alignment.BottomStart)
            .offset(x = indicatorOffset)
            .width(currentTabWidth)
    }


@Composable
fun TabItem(
    title: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Box(
        modifier = modifier
            .zIndex(2f)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 16.dp
            ),
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun getTabColor(
    tabPage: PagerState,
    selectedTabPage: Int,
): Color =
    if (tabPage.currentPage == selectedTabPage) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onBackground
            .copy(alpha = .6f)
    }



@Preview
@Composable
fun TabItemPrev() {
    TabItem(title = "", textColor = MaterialTheme.colorScheme.onBackground)
}