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
import androidx.compose.material.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.datepicker.calendar.getDates
import com.diyalotech.datepicker.ktx.withDayOfMonth
import com.diyalotech.datepicker.weekDayShortHeader
import io.github.aagitoex.nepdate.NepDate


@Composable
internal fun MonthView(
    viewDate: NepDate,
    selectedDate: NepDate,
    minDate: NepDate,
    maxDate: NepDate,
    onDateSelected: (NepDate) -> Unit
) {
    Column(
        Modifier
            .padding(start = 12.dp, end = 12.dp)
    ) {

        val calendarDatesData = remember { getDates(viewDate) }
        val today = remember { NepDate.now() }
        val selectedMonth =
            remember(selectedDate) { // this is the month where the date selection is
                viewDate.year == selectedDate.year && viewDate.month == selectedDate.month
            }
        val todayMonth = remember {
            viewDate.year == today.year && viewDate.month == today.month
        }

        LazyVerticalGrid(columns = GridCells.Fixed(7), userScrollEnabled = false) {
            for (x in 1 until calendarDatesData.first) {
                //empty views
                item(contentType = 1) { Box(Modifier.size(44.dp)) }
            }

            (1..calendarDatesData.second).forEach { day ->
                item(contentType = 2) {
                    val date = viewDate withDayOfMonth day
                    val enabled = date in minDate..maxDate

                    DateSelectionBox(
                        day,
                        selectedMonth && day == selectedDate.day,
                        todayMonth && day == today.day,
                        enabled
                    ) {
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
    if (selected) {
        Text(
            date.toString(),
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.primary)
                .wrapContentSize(Alignment.Center),
            style = TextStyle(
                fontSize = 12.sp
            ),
            color = MaterialTheme.colors.onPrimary
        )
    } else if (today) {
        Text(
            date.toString(),
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)

                .clickable(enabled) {
                    onClick()
                }
                .background(MaterialTheme.colors.primary.copy(0.2f))

                .wrapContentSize(Alignment.Center),
            style = TextStyle(
                fontSize = 12.sp
            ),
            color = MaterialTheme.colors.primary
        )
    } else {
        Text(
            date.toString(),
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .clickable(enabled) {
                    onClick()
                }
                .wrapContentSize(Alignment.Center),
            style = TextStyle(
                fontSize = 12.sp
            ),
            color = if (enabled) {
                MaterialTheme.colors.onSurface
            } else {
                MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled)
            }
        )
    }
}

@Composable
internal fun DayOfWeekHeader() {
    val locale = LocalConfiguration.current.locale
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.padding(horizontal = 12.dp),
        userScrollEnabled = false
    ) {
        weekDayShortHeader(locale).forEach { weekDay ->
            item {
                Text(
                    weekDay,
                    modifier = Modifier
                        .alpha(0.8f)
                        .size(40.dp)
                        .wrapContentSize(Alignment.Center),
                    style = TextStyle(fontSize = 14.sp, fontWeight = W600),
                )
            }
        }
    }
}

@Preview
@Composable
fun DayOfWeek_PREV() {
    Surface {
        DayOfWeekHeader()
    }
}