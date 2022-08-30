package com.diyalotech.datepicker.ui

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import com.diyalotech.datepicker.monthName
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import io.github.aagitoex.nepcal.R
import io.github.aagitoex.nepdate.Converter.fromADToBS
import io.github.aagitoex.nepdate.NepDate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * @param selectedDate - pre selected date, should be between minDate and maxDate else
 *  will be selected.
 * @param title - Custom title for the dialog
 * @see DialogProperties for dialogProperties
 * @param minDate - Minimum allowed date for selection.
 * @param maxDate - Maximum allowed date for selection.
 * @param onDismissRequest - lambda requests to hide dialog.
 * @param onDateChange - lambda to return selected date.
 * */
@OptIn(
    ExperimentalPagerApi::class, ExperimentalComposeUiApi::class,
)
@Composable
fun CalendarDialog(
    selectedDate: LocalDate,
    title: String = "Select date",
    dialogProperties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    minDate: LocalDate = NepDate.MIN.ad,
    maxDate: LocalDate = NepDate.MAX.ad,
    onDismissRequest: () -> Unit,
    onDateChange: (LocalDate) -> Unit
) {
    var yearPickerShowing by remember {
        mutableStateOf(false)
    }
    val minNepDate by remember {
        mutableStateOf(fromADToBS(minDate))
    }
    val maxNepDate by remember {
        mutableStateOf(fromADToBS(maxDate))
    }
    val todayNep by remember {
        mutableStateOf(NepDate.now())
    }

    val yearRange by remember {
        println("range ${minNepDate.year..maxNepDate.year}")
        mutableStateOf(minNepDate.year..maxNepDate.year)
    }
    val coroutineScope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = dialogProperties
    ) {
        var internalSelection by remember {
            mutableStateOf(
                if (selectedDate in minDate..maxDate) {
                    fromADToBS(selectedDate)
                } else {
                    minNepDate
                }
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 32.dp)
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colors.surface)
        ) {

            val pagerState = rememberPagerState(
                initialPage = (todayNep.year - yearRange.first) * 12 + (todayNep.month - 1)
            )
            var currentPage by remember { mutableStateOf(todayNep) }

            LaunchedEffect(pagerState) {
                // Collect from the pager state a snapshotFlow reading the currentPage
                snapshotFlow { pagerState.currentPage }.collectLatest { page ->
                    currentPage = NepDate(
                        yearRange.first + (page / 12),
                        page % 12 + 1,
                        1
                    )
                }
            }

            CalendarHeader(title, internalSelection)

            CalendarViewHeader(
                currentPage,
                yearPickerShowing,
                onClickNav = { left ->
                    coroutineScope.launch {
                        if (left) {
                            if (pagerState.currentPage > 0)
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        } else {
                            if (pagerState.currentPage < pagerState.pageCount)
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }, onDismissRequest = {
                    yearPickerShowing = !yearPickerShowing
                }
            )

            Box(modifier = Modifier.weight(1f, false)) {
                androidx.compose.animation.AnimatedVisibility(
                    yearPickerShowing,
                    modifier = Modifier
                        .zIndex(0.7f)
                        .background(MaterialTheme.colors.surface)
                        .clipToBounds(),
                    enter = slideInVertically(initialOffsetY = { -it }),
                    exit = slideOutVertically(targetOffsetY = { -it })
                ) {
                    YearPicker(currentPage.year, pagerState, yearRange) {
                        yearPickerShowing = false
                    }
                }
                Column {
                    DayOfWeekHeader()
                    HorizontalPager(
                        count = (yearRange.count()) * 12,
                        state = pagerState
                    ) { page ->
                        val pageDate = remember {
                            NepDate(yearRange.first + (page / 12), page % 12 + 1, 1)
                        }
                        MonthView(
                            pageDate,
                            internalSelection,
                            minNepDate,
                            maxNepDate
                        ) {
                            internalSelection = it
                        }
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                TextButton(onClick = onDismissRequest) {
                    Text(text = stringResource(id = R.string.cancel))
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = {
                    onDateChange(internalSelection.ad)
                    onDismissRequest()
                }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        }
    }

}

@Composable
private fun CalendarHeader(title: String, selectedDate: NepDate) {
    Box(
        Modifier
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(start = 24.dp, end = 24.dp)) {
            Text(
                text = title,
                modifier = Modifier.paddingFromBaseline(top = 24.dp),
                style = TextStyle(fontSize = 12.sp)
            )

            Box(
                Modifier
                    .fillMaxWidth()
                    .paddingFromBaseline(top = 0.dp)
            ) {
                val locale = LocalConfiguration.current.locale

                Text(
                    text = "${selectedDate.monthName(locale)} ${selectedDate.day}, ${selectedDate.year}",
                    modifier = Modifier.align(Alignment.CenterStart),
                    style = TextStyle(fontSize = 30.sp, fontWeight = W400)
                )
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun Preview_MonthView() {
    Surface {
        CalendarDialog(
            selectedDate = LocalDate.now(),
            onDismissRequest = {}
        ) {

        }
    }
}