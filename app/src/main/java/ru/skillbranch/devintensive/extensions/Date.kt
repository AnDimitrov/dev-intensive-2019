package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff: Long = this.time - date.time
    return when {
        diff / SECOND in 1..45 -> "через несколько секунд"
        diff / SECOND in 45..75 -> "через минуту"
        diff / SECOND in 75..45 * 60 -> "через ${pluralize((diff / MINUTE).toInt(), listOf("минуту", "минуты", "минут"))}"
        diff / MINUTE in 45..75 -> "через час"
        diff / MINUTE in 75..22 * 60 -> "через ${pluralize((diff / HOUR).toInt(), listOf("час", "часа", "часов"))}"
        diff / HOUR in 22..26 -> "через день"
        diff / HOUR in 26..360 * 24 -> "через ${pluralize((diff / DAY).toInt(), listOf("день", "дня", "дней"))}"
        diff / DAY > 360 -> "более чем через год"

        diff / SECOND in -45 until 0 -> "несколько секунд назад"
        diff / SECOND in -75 until -45 -> "минуту назад"
        diff / SECOND in -45 * 60 until -75 -> "${pluralize((-diff / MINUTE).toInt(), listOf("минуту", "минуты", "минут"))} назад"
        diff / MINUTE in -75 until -45 -> "час назад"
        diff / MINUTE in -22 * 60 until -75 -> "${pluralize((-diff / HOUR).toInt(), listOf("час", "часа", "часов"))} назад"
        diff / HOUR in -26 until -22 -> "день назад"
        diff / HOUR in -360 * 24 until -26 -> "${pluralize((-diff / DAY).toInt(), listOf("день", "дня", "дней"))} назад"
        diff / DAY < -360 -> "более года назад"

        else -> "только что"
    }
}

fun pluralize(count: Int, words: List<String>): String {
    val cases = intArrayOf(2, 0, 1, 1, 1, 2)
    return "$count ${words[ if (count % 100 in 5..20) 2 else cases[minOf(count % 10, 5)]]}"
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(num: Int): String {
        return when (this) {
            SECOND -> pluralize(num, listOf("секунду", "секунды", "секунд"))
            MINUTE -> pluralize(num, listOf("минуту", "минуты", "минут"))
            HOUR -> pluralize(num, listOf("час", "часа", "часов"))
            DAY -> pluralize(num, listOf("день", "дня", "дней"))
        }
    }
}