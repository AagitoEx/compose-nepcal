package com.diyalotech.datepicker.date

import java.lang.StrictMath.abs
import java.security.InvalidParameterException
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.sign

data class NepDate(
    val year: Int,
    val month: Int,
    val day: Int,
) : Comparable<NepDate> {

    var locale: Locale = Locale.getDefault()

    val adEquivalent by lazy {
        fromBSToAD(this)
    }

    val monthName by lazy {
        if (locale.language == Locale("ne").language) {
            monthMapLocale[monthMap[month]]
        } else {
            monthMap[month]
        }
    }

    val monthNameShort by lazy {
        if (locale.language == Locale("ne").language) {
            monthMapLocale[monthMap[month]]
        } else {
            monthMap[month]
        }
    }

    val dayOfWeek = adEquivalent.dayOfWeek.value

    val weekDay by lazy {
        if (locale.language == Locale("ne").language) {
            weekDayMap[dayOfWeek]?.first
        } else {
            weekDayMapLocale[weekDayMap[dayOfWeek]?.first]
        }
    }

    companion object {
        @JvmStatic
        fun now(): NepDate {
            return fromADToBS(LocalDate.now())
        }

        val MIN: NepDate = NepDate(Constants.bsLBoundY, Constants.bsLBoundM, Constants.bsLBoundD)

        val MAX: NepDate = NepDate(Constants.bsUBoundY, Constants.bsUBoundM, Constants.bsUBoundD)
    }

    init {
        val year = Constants.bsDaysInMonthByYear[year]
            ?: throw UnsupportedOperationException("Date out of range.")
        if (month !in 1..12) throw InvalidParameterException("Invalid month.")
        if (day !in 1..year[month - 1]) throw InvalidParameterException("Invalid day in a month")
    }

    /**
     * format: yyyy-MM-dd
     * */
    override fun toString(): String {
        return "$year-$month-$day"
    }

    /**
     * format: yyyy-MM-dd
     * */
    fun formatMonth(nepali: Boolean = false): String {
        return if (nepali)
            "$year-${monthMapLocale[monthList[month - 1]]}-$day"
        else
            "$year-${monthList[month - 1]}-$day"
    }

    infix fun addMonths(addition: Int): NepDate {
        if (addition.sign >= 0) {
            var (year, month, day) = this

            var newMonth = month + addition

            while (newMonth > 12) {
                newMonth -= 12
                year++
            }

            val yearM = Constants.bsDaysInMonthByYear[year]!!

            if (day !in 0..yearM[newMonth - 1]) {
                day = yearM[newMonth - 1]
            }

            return NepDate(year, newMonth, day)
        } else {
            throw InvalidParameterException("Days cannot be negative")
        }
    }

    infix fun subtractMonths(subtract: Int): NepDate {
        var (year, month, day) = this

        var newMonth = month - abs(subtract)

        while (newMonth < 1) {
            newMonth += 12
            year--
        }

        val yearM = Constants.bsDaysInMonthByYear[year]!!

        if (day !in 0..yearM[newMonth - 1]) {
            day = yearM[newMonth - 1]
        }

        return NepDate(year, newMonth, day)
    }

    infix fun addDays(addition: Int): NepDate {
        if (addition.sign >= 0) {
            var (year, month, day) = this

            var newDays = day + addition

            var propagate = true
            while (propagate) {
                val yearM = Constants.bsDaysInMonthByYear[year]!!

                if (month > 12) {
                    month -= 12
                    year++
                    continue
                }

                println(yearM[month - 1])
                if (newDays > yearM[month - 1]) {
                    newDays -= yearM[month - 1]
                    month++
                    continue
                }

                propagate = false
            }


            return NepDate(year, month, newDays)
        } else {
            throw InvalidParameterException("Days cannot be negative")
        }
    }

    infix fun subtractDays(subtract: Int): NepDate {
        var (year, month, day) = this

        var newDays = day - abs(subtract)

        var propagate = true
        while (propagate) {
            val yearM = Constants.bsDaysInMonthByYear[year]!!

            if (month < 1) {
                month += 12
                year--
                continue
            }

            if (newDays <= 0) {
                newDays += yearM[month - 1]
                month--
                continue
            }

            propagate = false
        }

        return NepDate(year, month, newDays)
    }

    infix fun addYears(addition: Int): NepDate {
        if (addition.sign >= 0) {
            return NepDate(this.year + addition, this.month, this.day)
        } else {
            throw InvalidParameterException("Days cannot be negative")
        }
    }

    infix fun subtractYears(subtract: Int): NepDate {
        return NepDate(this.year - abs(subtract), this.month, this.day)
    }

    infix fun daysBetween(nepDate: NepDate): Long {
        val ad1 = fromBSToAD(this)
        val ad2 = fromBSToAD(nepDate)
        return ChronoUnit.DAYS.between(ad1, ad2)
    }

    infix fun withDayOfMonth(nday: Int): NepDate {
        return NepDate(year, month, nday)
    }

    override operator fun compareTo(other: NepDate): Int {
        val first = ("$year" + "$month".padStart(2, '0') + "$day".padStart(2, '0')).toLong()
        val second = ("${other.year}" + "${other.month}".padStart(2, '0') + "${other.day}".padStart(
            2,
            '0'
        )).toLong()

        return when {
            first > second -> {
                1
            }
            first == second -> {
                0
            }
            else -> {
                -1
            }
        }
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

val weekDayMapLocale = mapOf(
    "आइतबार" to "Sunday",
    "सोमबार" to "Monday",
    "मंगलबार" to "Tuesday",
    "बुधबार" to "Wednesday",
    "बिहिबार" to "Thursday",
    "शुक्रबार" to "Friday",
    "शनिबार" to "Saturday",
)

fun weekDayShortHeader(locale: Locale): List<String> {
    return if (locale.language == Locale("ne").language) {
        (weekDayMap.values.toList().subList(6, 7) + weekDayMap.values.toList()
            .subList(0, 6)).map { it.second }
    } else {
        weekDayMapLocale.values.map { it.substring(0, 3) }
    }
}

val weekDayMap = mapOf(
    1 to ("सोमबार" to "सो"),
    2 to ("मंगलबार" to "मं"),
    3 to ("बुधबार" to "बु"),
    4 to ("बिहिबार" to "बि"),
    5 to ("शुक्रबार" to "शु"),
    6 to ("शनिबार" to "श"),
    7 to ("आइतबार" to "आ")
)