package com.diyalotech.datepicker.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.diyalotech.datepicker.calendar.getDates
import com.diyalotech.datepicker.date.NepDate
import com.diyalotech.datepicker.date.weekDayMap
import com.diyalotech.datepicker.date.weekDayShortHeader


@Composable
internal fun MonthView(
    viewDate: NepDate,
    selectedDate: NepDate,
    minDate: NepDate,
    maxDate: NepDate,
    today: NepDate = NepDate.now(),
    onDateSelected: (NepDate) -> Unit
) {
    Column(
        Modifier
            .padding(start = 12.dp, end = 12.dp)
            .testTag("dialog_date_calendar")
    ) {

        val calendarDatesData = remember { getDates(viewDate) }
        val dayList = remember { IntRange(1, calendarDatesData.second) }
        val possibleSelected = remember(selectedDate) {
            viewDate.year == selectedDate.year && viewDate.month == selectedDate.month
        }

        LazyVerticalGrid(columns = GridCells.Fixed(7)) {
            for (x in 1 until calendarDatesData.first) {
                //empty views
                item(contentType = 1) { Box(Modifier.size(40.dp)) }
            }

            dayList.forEach { day ->
                item(contentType = 2) {
                    val isSelected = remember(selectedDate, possibleSelected) {
                        possibleSelected && day == selectedDate.day
                    }
                    val isToday = remember(today) {
                        possibleSelected && day == today.day
                    }

                    val date = viewDate withDayOfMonth day
                    val enabled = date in minDate..maxDate

                    DateSelectionBox(day, isSelected, isToday, enabled) {
                        onDateSelected(date)
                    }
                }
            }
        }
    }
}


@Composable
private fun DateSelectionBox(
    date: Int,
    selected: Boolean,
    today: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Box(
            Modifier
                .clip(CircleShape)
                .clickable(enabled) {
                    onClick()
                }
                .background(
                    when {
                        selected -> MaterialTheme.colors.primary
                        today -> MaterialTheme.colors.primary.copy(0.2f)
                        else -> MaterialTheme.colors.surface
                    }
                )
                .size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                date.toString(),
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .wrapContentSize(Alignment.Center),
                style = TextStyle(
                    fontSize = 12.sp
                ),
                color = when {
                    selected -> MaterialTheme.colors.onPrimary
                    today -> MaterialTheme.colors.primary
                    else -> {
                        if (enabled) {
                            MaterialTheme.colors.onSurface
                        } else {
                            MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled)
                        }
                    }
                }
            )
        }
    }
}

@Composable
internal fun DayOfWeekHeader() {
    val locale = LocalConfiguration.current.locale
    Row(
        modifier = Modifier
            .height(40.dp)
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(7)) {

            weekDayShortHeader(locale).forEach { weekDay ->
                item {
                    Box(Modifier.size(40.dp)) {
                        Text(
                            weekDay,
                            modifier = Modifier
                                .alpha(0.8f)
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center),
                            style = TextStyle(fontSize = 14.sp, fontWeight = W600),
                        )
                    }
                }
            }
        }
    }
}