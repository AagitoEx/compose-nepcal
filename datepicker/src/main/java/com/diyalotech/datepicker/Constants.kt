package com.diyalotech.datepicker

import io.github.aagitoex.nepdate.NepDate
import java.util.*

private val weekDayMap = mapOf(
    1 to ("सोमबार" to "सो"),
    2 to ("मंगलबार" to "मं"),
    3 to ("बुधबार" to "बु"),
    4 to ("बिहिबार" to "बि"),
    5 to ("शुक्रबार" to "शु"),
    6 to ("शनिबार" to "श"),
    7 to ("आइतबार" to "आ")
)

private val weekDayMapLocale = mapOf(
    "आइतबार" to "Sunday",
    "सोमबार" to "Monday",
    "मंगलबार" to "Tuesday",
    "बुधबार" to "Wednesday",
    "बिहिबार" to "Thursday",
    "शुक्रबार" to "Friday",
    "शनिबार" to "Saturday",
)

internal fun weekDayShortHeader(locale: Locale): List<String> {
    return if (locale.language == Locale("ne").language) {
        (weekDayMap.values.toList().subList(6, 7) + weekDayMap.values.toList()
            .subList(0, 6)).map { it.second }
    } else {
        weekDayMapLocale.values.map { it.substring(0, 3) }
    }
}


internal val monthList = arrayOf(
    "Baisakh",
    "Jestha",
    "Ashar",
    "Shrawan",
    "Bhadra",
    "Ashoj",
    "Kartik",
    "Mangsir",
    "Poush",
    "Magh",
    "Falgun",
    "Chaitra",
)

internal val monthMapLocale = mapOf(
    "Baisakh" to "बैशाख",
    "Jestha" to "जेठ",
    "Ashar" to "असार",
    "Shrawan" to "साउन",
    "Bhadra" to "भदौ",
    "Ashoj" to "असोज",
    "Kartik" to "कार्तिक",
    "Mangsir" to "मंसिर",
    "Poush" to "पौष",
    "Magh" to "माघ",
    "Falgun" to "फागुन",
    "Chaitra" to "चैत"
)

val monthMap = mapOf(
    1 to "Baisakh",
    2 to "Jestha",
    3 to "Ashar",
    4 to "Shrawan",
    5 to "Bhadra",
    6 to "Ashoj",
    7 to "Kartik",
    8 to "Mangsir",
    9 to "Poush",
    10 to "Magh",
    11 to "Falgun",
    12 to "Chaitra",
)

fun NepDate.monthName(locale: Locale): String {
    return if (locale.language == Locale("ne").language) {
        monthMapLocale[monthMap[month]] ?: ""
    } else {
        monthMap[month] ?: ""
    }
}