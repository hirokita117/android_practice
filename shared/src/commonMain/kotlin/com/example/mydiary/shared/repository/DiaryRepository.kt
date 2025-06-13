package com.example.mydiary.shared.repository

import com.example.mydiary.shared.model.DiaryEntry
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

interface DiaryRepository {
    suspend fun getDiaryEntries(month: Month, year: Int): List<DiaryEntry>
    suspend fun getDiaryEntry(id: String): DiaryEntry?
    suspend fun addDiaryEntry(entry: DiaryEntry)
    suspend fun updateDiaryEntry(entry: DiaryEntry)
    suspend fun deleteDiaryEntry(id: String)
    suspend fun getEntriesByDateRange(startDate: LocalDate, endDate: LocalDate): List<DiaryEntry>
}
