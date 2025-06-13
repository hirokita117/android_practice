package com.example.mydiary.shared.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class DiaryEntry(
    val id: String,
    val title: String,
    val content: String,
    val date: LocalDate,
    val mood: Mood,
    val weather: Weather,
    val createdAt: Instant,
    val updatedAt: Instant
)
