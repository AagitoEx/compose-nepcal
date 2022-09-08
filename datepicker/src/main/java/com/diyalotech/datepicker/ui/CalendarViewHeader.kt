package com.diyalotech.datepicker.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.datepicker.monthName
import io.github.aagitoex.nepdate.NepDate

@Composable
internal fun CalendarViewHeader(
    viewDate: NepDate,
    yearPickerShowing: Boolean,
    onClickNav: (left: Boolean) -> Unit,
    onDismissRequest: () -> Unit
) {
    val locale = LocalConfiguration.current.locale
    val dropDownRotation = remember(yearPickerShowing) {
        if (yearPickerShowing) 180f else 0f
    }

    Row(
        Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .clip(CircleShape)
                .clickable(onClick = onDismissRequest)
                .padding(start = 6.dp, end = 4.dp)
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "${viewDate.monthName(locale)} ${viewDate.year}",
                modifier = Modifier
                    .padding(start = 2.dp),
                style = TextStyle(fontSize = 14.sp, fontWeight = W600)
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "Year Selector",
                modifier = Modifier
                    .size(24.dp)
                    .rotate(dropDownRotation)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            Icons.Default.KeyboardArrowLeft,
            contentDescription = "Previous Month",
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .clickable {
                    onClickNav(true)
                }

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
                .clickable {
                    onClickNav(false)
                }
                .padding(8.dp),
        )
    }
}

@Preview
@Composable
fun CalendarViewHeader_prev() {
    Surface {
        CalendarViewHeader(NepDate.now(), false, {}, {})
    }
}