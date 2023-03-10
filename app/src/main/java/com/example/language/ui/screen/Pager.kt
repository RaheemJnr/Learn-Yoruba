package com.example.language.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.language.ui.theme.Dimens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pager(
    pagerState : PagerState,
    tabsTitles : List<TabItems>,
    scope : CoroutineScope,
    horizontalPagerLayout : @Composable () -> Unit = {}
) {
    TabRowComposable(
        pagerState,
        tabsTitles,
        scope = scope,
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
    scope : CoroutineScope,
    modifier : Modifier = Modifier
) {
    TabRow(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            CustomTabIndicator(currentTabPosition = tabPositions[pagerState.currentPage])
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        divider = {}
    ) {
        // Add tabs for all of our pages
        tabsTitles.forEachIndexed { index, title ->

            TabItem(
                title = title.value,
                textColor = getTabColor(
                    tabPage = pagerState,
                    selectedTabPage = index

                )
            ) {
                scope.launch {
                    pagerState.animateScrollToPage(page = index)
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(Dimens.MediumPadding.size))
}

//TabRowDefaults.Indicator(
//Modifier
//.pagerTabIndicatorOffset(pagerState, tabPositions)
//.height(6.dp)
//.padding(horizontal = 48.dp)
//.clip(RoundedCornerShape(8.dp)),
//color = Color.Green.copy(alpha = 0.6f)
//)