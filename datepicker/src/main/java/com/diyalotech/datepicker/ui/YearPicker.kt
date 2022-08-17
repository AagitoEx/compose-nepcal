package com.diyalotech.datepicker.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.datepicker.calendar.YEAR_RANGE
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import io.github.aagitoex.nepdate.NepDate
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun YearPicker(
    viewDate: NepDate,
    pagerState: PagerState,
    onDismissYearPicker: () -> Unit
) {
    val gridState = rememberLazyGridState((viewDate.year - YEAR_RANGE.first) / 3)
    val coroutineScope = rememberCoroutineScope()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        state = gridState
    ) {
        itemsIndexed(YEAR_RANGE.toList()) { _, item ->
            val selected = remember { item == viewDate.year }

            YearPickerItem(year = item, selected = selected) {
                if (!selected) {
                    coroutineScope.launch {
                        pagerState.scrollToPage(
                            pagerState.currentPage + (item - viewDate.year) * 12
                        )
                    }
                }
                onDismissYearPicker()
            }
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