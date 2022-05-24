package com.diyalotech.datepicker.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.datepicker.date.NepDate
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun CalendarViewHeader(
    viewDate: NepDate,
    yearPickerShowing: Boolean,
    pagerState: PagerState,
    onDismissYearPicker: () -> Unit
) {
    viewDate.locale = LocalConfiguration.current.locale
    val coroutineScope = rememberCoroutineScope()
    val yearDropdownIcon = remember(yearPickerShowing) {
        if (yearPickerShowing) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown
    }

    Box(
        Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .clip(CircleShape)
                .align(Alignment.CenterStart)
                .clickable(onClick = onDismissYearPicker)
                .padding(start = 6.dp, end = 4.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(
                "${viewDate.monthName} ${viewDate.year}",
                modifier = Modifier
                    .paddingFromBaseline(top = 16.dp)
                    .padding(start = 2.dp),
                style = TextStyle(fontSize = 14.sp, fontWeight = W600)
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                yearDropdownIcon,
                contentDescription = "Year Selector",
                modifier = Modifier.size(24.dp)
            )
        }

        Row(
            Modifier
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = "Previous Month",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .clickable(
                        onClick = {
                            coroutineScope.launch {
                                if (pagerState.currentPage - 1 >= 0)
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    )
                    .padding(8.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "Next Month",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .size(24.dp)
                    .clickable(
                        onClick = {
                            coroutineScope.launch {
                                if (pagerState.currentPage + 1 < pagerState.pageCount)
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    )
                    .padding(8.dp),
            )
        }
    }
}