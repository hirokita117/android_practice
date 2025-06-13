package com.example.mydiary.shared.data.local.converter

import kotlinx.datetime.LocalDate
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
// import org.junit.Assert.fail // For JUnit 4 style fail

class DateTimeConvertersTest {

    private val converters = DateTimeConverters()

    @Test
    fun `localDateToString and fromLocalDateString basic conversion`() {
        val date = LocalDate(2023, 10, 26)
        val dateString = converters.localDateToString(date)
        assertEquals("2023-10-26", dateString)
        val convertedDate = converters.fromLocalDateString(dateString)
        assertEquals(date, convertedDate)
    }

    @Test
    fun `localDateToString handles null`() {
        assertNull(converters.localDateToString(null))
    }

    @Test
    fun `fromLocalDateString handles null`() {
        assertNull(converters.fromLocalDateString(null))
    }

    @Test
    fun `fromLocalDateString handles invalid format`() {
        // kotlinx.datetime.LocalDate.parse() throws DateTimeFormatException for invalid formats.
        // This test verifies that behavior.
        var exceptionThrown = false
        try {
            converters.fromLocalDateString("invalid-date-format")
        } catch (e: kotlinx.datetime.DateTimeFormatException) {
            exceptionThrown = true
        }
        assertEquals(true, exceptionThrown, "DateTimeFormatException was expected for invalid date format.")
    }

    @Test
    fun `instantToTimestamp and fromTimestamp basic conversion`() {
        val epochMillis = 1678886400000L // Example: 2023-03-15T12:00:00Z
        val instant = kotlinx.datetime.Instant.fromEpochMilliseconds(epochMillis)

        val timestamp = converters.dateToTimestamp(instant)
        assertEquals(epochMillis, timestamp)

        val convertedInstant = converters.fromTimestamp(timestamp)
        assertEquals(instant, convertedInstant)
    }

    @Test
    fun `dateToTimestamp handles null`() {
        assertNull(converters.dateToTimestamp(null))
    }

    @Test
    fun `fromTimestamp handles null`() {
        assertNull(converters.fromTimestamp(null))
    }

    @Test
    fun `enum Mood conversion`() {
        val moodHappy = com.example.mydiary.shared.model.Mood.HAPPY
        val moodString = converters.fromMood(moodHappy)
        assertEquals("HAPPY", moodString)
        val convertedMood = converters.toMood(moodString)
        assertEquals(moodHappy, convertedMood)
    }

    @Test
    fun `enum Weather conversion`() {
        val weatherSunny = com.example.mydiary.shared.model.Weather.SUNNY
        val weatherString = converters.fromWeather(weatherSunny)
        assertEquals("SUNNY", weatherString)
        val convertedWeather = converters.toWeather(weatherString)
        assertEquals(weatherSunny, convertedWeather)
    }
}
