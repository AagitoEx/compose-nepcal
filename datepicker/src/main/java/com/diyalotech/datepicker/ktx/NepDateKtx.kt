package com.diyalotech.datepicker.ktx

import io.github.aagitoex.nepdate.NepDate

infix fun NepDate.withDayOfMonth(days: Int): NepDate {
    return NepDate(this.year, this.month, days)
}

val NepDate.dayOfWeek: Int
    get() {
        return this.ad.dayOfWeek.value
    }
