package com.example.y2s2_assignment2.util

import java.time.LocalDate

object DateProvider {
    fun today(): LocalDate = LocalDate.now()
    fun keyFor(date: LocalDate): String = date.toString()
}
