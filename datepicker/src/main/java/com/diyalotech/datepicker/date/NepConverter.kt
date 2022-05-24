package com.diyalotech.datepicker.date

import com.diyalotech.datepicker.date.Constants.adLBoundD
import com.diyalotech.datepicker.date.Constants.adLBoundM
import com.diyalotech.datepicker.date.Constants.adLBoundY
import com.diyalotech.datepicker.date.Constants.bsDaysInMonthByYear
import com.diyalotech.datepicker.date.Constants.bsLBoundY
import com.diyalotech.datepicker.date.Constants.bsUBoundY
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

fun fromADToBS(date: Date): NepDate {
    val local = date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    return fromADToBS(local)
}

fun fromADToBS(date: LocalDate): NepDate {
    val gLow = LocalDate.of(adLBoundY, adLBoundM, adLBoundD)
    val daysElapsed = ChronoUnit.DAYS.between(gLow, date)

    return nepDateFromDayElapsed(daysElapsed) ?: throw Throwable("Could not covert.")
}

fun fromBSToAD(date: NepDate): LocalDate {
    val glow = LocalDate.of(adLBoundY, adLBoundM, adLBoundD)

    //count days in year
    var totalDiff = 0L
    for (i in bsLBoundY until date.year) {
        totalDiff += bsDaysInMonthByYear[i]!![12]
    }

    //count days in month
    for (i in 0 until date.month - 1) {
        totalDiff += bsDaysInMonthByYear[date.year]!![i]
    }

    //add remaining days
    totalDiff += (date.day - 1)

    return glow.plusDays(totalDiff)
}

private fun nepDateFromDayElapsed(daysElapsed: Long): NepDate? {
    var elapsed = daysElapsed.toInt()

    for (i in bsLBoundY..bsUBoundY) {

        val year = bsDaysInMonthByYear[i]!!

        if (year[12] <= elapsed) {
            elapsed -= year[12]
            continue
        }

        for (j in 0 until 12) {
            val days = year[j]
            if (days <= elapsed) {
                elapsed -= days
                continue
            }

            return NepDate(i, j + 1, elapsed + 1)
        }
    }
    return null
}
