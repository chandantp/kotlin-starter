package com.starter

import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime

fun main() {
    println("Hello!")

    val taskEndTime = findTaskEndTime(
        1000,
        LocalDateTime.of(2021, 8, 20, 10, 0)
    )

    println("TaskEndTime = $taskEndTime")
}

data class TimeOfDay(val hour: Int, val minute: Int) {
    init {
        require(hour in 0..23 && minute in 0..59)
    }
}

fun findTaskEndTime(
    taskDurationInMinutes: Long,
    taskStartTime: LocalDateTime,
    startOfBusinessDay: TimeOfDay = TimeOfDay(9, 0), // 9 AM
    endOfBusinessDay: TimeOfDay = TimeOfDay(17, 0) // 5 PM
): LocalDateTime {

    var currentTime = taskStartTime.withSecond(0)

    if (currentTime.dayOfWeek == DayOfWeek.SATURDAY || currentTime.dayOfWeek == DayOfWeek.SUNDAY) {
        throw IllegalArgumentException("Task cannot start on the weekends!")
    }

    val businessDayStartTime = currentTime.withHour(startOfBusinessDay.hour).withMinute(startOfBusinessDay.minute)
    val businessDayEndTime = currentTime.withHour(endOfBusinessDay.hour).withMinute(endOfBusinessDay.minute)
    val minutesInBusinessDay = Duration.between(businessDayStartTime, businessDayEndTime).toMinutes()

    when {
        currentTime < businessDayStartTime ->
            throw IllegalArgumentException("taskStartTime '$taskStartTime' is before business hours!")
        currentTime > businessDayEndTime ->
            throw IllegalArgumentException("taskStartTime '$taskStartTime' is after business hours!")
    }

    var remainingDuration = taskDurationInMinutes
    val taskFirstDayMinutes = Duration.between(currentTime, businessDayEndTime).toMinutes()

    if (remainingDuration > taskFirstDayMinutes) {
        currentTime = currentTime.plusMinutes(taskFirstDayMinutes)
        remainingDuration -= taskFirstDayMinutes
    }
    else {
        currentTime = currentTime.plusMinutes(taskDurationInMinutes)
        remainingDuration -= taskDurationInMinutes
    }

    while(remainingDuration > 0) {

        currentTime = currentTime
            .withHour(startOfBusinessDay.hour)
            .withMinute(startOfBusinessDay.minute)
            .plusDays(1)

        if (currentTime.dayOfWeek != DayOfWeek.SATURDAY && currentTime.dayOfWeek != DayOfWeek.SUNDAY) {
            if (remainingDuration > minutesInBusinessDay) {
                currentTime = currentTime.plusMinutes(minutesInBusinessDay)
                remainingDuration -= minutesInBusinessDay
            }
            else {
                currentTime = currentTime.plusMinutes(remainingDuration)
                remainingDuration = 0
            }
        }
    }

    return currentTime
}
