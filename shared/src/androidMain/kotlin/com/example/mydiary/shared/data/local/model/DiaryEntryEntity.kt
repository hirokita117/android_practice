package com.example.mydiary.shared.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mydiary.shared.model.Mood
import com.example.mydiary.shared.model.Weather
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

@Entity(tableName = "diary_entries")
data class DiaryEntryEntity(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val date: LocalDate,
    val mood: Mood,
    val weather: Weather,
    val createdAt: Instant,
    val updatedAt: Instant
)
