package com.example.mydiary.shared.data.local.converter

import androidx.room.TypeConverter
import com.example.mydiary.shared.model.Mood
import com.example.mydiary.shared.model.Weather
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

class DateTimeConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? {
        return value?.let { Instant.fromEpochMilliseconds(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Instant?): Long? {
        return date?.toEpochMilliseconds()
    }

    @TypeConverter
    fun fromLocalDateString(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun localDateToString(date: LocalDate?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun fromMood(mood: Mood?): String? = mood?.name

    @TypeConverter
    fun toMood(name: String?): Mood? = name?.let { Mood.valueOf(it) }

    @TypeConverter
    fun fromWeather(weather: Weather?): String? = weather?.name

    @TypeConverter
    fun toWeather(name: String?): Weather? = name?.let { Weather.valueOf(it) }
}
