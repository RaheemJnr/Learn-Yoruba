package com.example.language.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.language.ui.theme.Dimens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.android.material.tabs.TabItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


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

    Pager(pagerState = pagerState, tabsTitles = tabsTitles)

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pager(
    pagerState : PagerState,
    tabsTitles : List<TabItems>,
    horizontalPagerLayout : @Composable () -> Unit = {}
) {
    TabRowComposable(
        pagerState,
        tabsTitles,
        modifier = Modifier.padding(horizontal = Dimens.MediumPadding.size),
    )
    //
    horizontalPagerLayout()
}

@Composable
@OptIn(ExperimentalPagerApi::class)
fun TabRowComposable(
    pagerState : PagerState,
    tabsTitles : List<TabItems>,
    modifier : Modifier = Modifier
) {
    TabRow(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .height(6.dp)
                    .padding(horizontal = 48.dp)
                    .clip(RoundedCornerShape(8.dp)),
                color = Color.Black.copy(alpha = 0.6f)
            )
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        divider = {}
    ) {
        // Add tabs for all of our pages
        tabsTitles.forEachIndexed { _, title ->
            Text(
                text = title.value,
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 16.dp
                ),

                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

//            TabItem(
//                title = title.value,
//                textColor = getTabColor(
//                    tabPage = pagerState,
//                    selectedTabPage = index
//
//                )
//            ) {
//                scope.launch {
//                    pagerState.animateScrollToPage(page = index)
//                }
//            }
        }
    }
    Spacer(modifier = Modifier.height(Dimens.MediumPadding.size))
}