package com.example.mydiary.shared.repository

import com.example.mydiary.shared.model.DiaryEntry
import kotlinx.datetime.LocalDate

interface DiaryRepository {
    suspend fun getDiaryEntries(date: LocalDate): Result<List<DiaryEntry>>
    suspend fun getDiaryEntry(id: String): Result<DiaryEntry?>
    suspend fun addDiaryEntry(entry: DiaryEntry): Result<Unit>
    suspend fun updateDiaryEntry(entry: DiaryEntry): Result<Unit>
    suspend fun deleteDiaryEntry(id: String): Result<Unit>
}
