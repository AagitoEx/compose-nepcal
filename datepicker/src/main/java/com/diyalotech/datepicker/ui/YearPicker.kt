package com.diyalotech.datepicker.ui

import android.util.Range
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun YearPicker(
    year: Int,
    pagerState: PagerState,
    yearRange: IntRange,
    onDismissYearPicker: () -> Unit
) {
    val gridState = rememberLazyGridState((year - yearRange.first) / 3)
    val coroutineScope = rememberCoroutineScope()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        state = gridState,
        modifier = Modifier
            .fillMaxSize()
    ) {

        yearRange.forEach { item ->
            item {
                val selected = remember { item == year }

                YearPickerItem(year = item, selected = selected) {
                    if (!selected) {
                        coroutineScope.launch {
                            pagerState.scrollToPage(
                                pagerState.currentPage + (item - year) * 12
                            )
                        }
                    }
                    onDismissYearPicker()
                }
            }
        }
    }

    SideEffect {
        coroutineScope.launch {
            gridState.scrollToItem(yearRange.indexOf(year))
        }
    }
}


@Composable
private fun YearPickerItem(
    year: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Box(
            Modifier
                .size(88.dp, 52.dp)
                .padding(8.dp)
                .clip(CircleShape)
                .background(
                    if (selected)
                        MaterialTheme.colors.primary
                    else
                        MaterialTheme.colors.surface
                )
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                year.toString(),
                fontSize = 18.sp,
                color = if (selected)
                    MaterialTheme.colors.onPrimary
                else
                    MaterialTheme.colors.onSurface
            )
        }
    }
}