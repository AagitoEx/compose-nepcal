package com.github.aagitoex.nepcal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.diyalotech.datepicker.ui.CalendarDialog
import com.github.aagitoex.nepcal.ui.theme.NepCalTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NepCalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    var showDateDialog by remember { mutableStateOf(false) }
                    Text(text = "Click anywhere to open calender", Modifier.clickable {
                        showDateDialog = true
                    })

                    if (showDateDialog) {
                        CalendarDialog(
                            selectedDate = LocalDate.now()!!,
                            onDismissRequest = {
                                showDateDialog = false
                            },
                            onDateChange = { localDate: LocalDate ->

                            }
                        )
                    }
                }
            }
        }

    }
}