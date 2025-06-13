package com.example.mydiary.shared.repository

import com.example.mydiary.shared.data.local.dao.DiaryDao
import com.example.mydiary.shared.data.local.model.DiaryEntryEntity
import com.example.mydiary.shared.model.DiaryEntry
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

class DiaryRepositoryImpl(private val diaryDao: DiaryDao) : DiaryRepository {

    override suspend fun getDiaryEntries(month: Month, year: Int): List<DiaryEntry> {
        val formattedMonth = month.value.toString().padStart(2, '0')
        val formattedYear = year.toString()
        return diaryDao.getDiaryEntries(formattedMonth, formattedYear).map { it.toDomain() }
    }

    override suspend fun getDiaryEntry(id: String): DiaryEntry? {
        return diaryDao.getDiaryEntry(id)?.toDomain()
    }

    override suspend fun addDiaryEntry(entry: DiaryEntry) {
        diaryDao.insertDiaryEntry(entry.toEntity())
    }

    override suspend fun updateDiaryEntry(entry: DiaryEntry) {
        diaryDao.updateDiaryEntry(entry.toEntity())
    }

    override suspend fun deleteDiaryEntry(id: String) {
        diaryDao.deleteDiaryEntry(id)
    }

    override suspend fun getEntriesByDateRange(startDate: LocalDate, endDate: LocalDate): List<DiaryEntry> {
        return diaryDao.getEntriesByDateRange(startDate, endDate).map { it.toDomain() }
    }

    // Helper mapping functions
    private fun DiaryEntryEntity.toDomain(): DiaryEntry {
        return DiaryEntry(
            id = this.id,
            title = this.title,
            content = this.content,
            date = this.date,
            mood = this.mood,
            weather = this.weather,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }

    private fun DiaryEntry.toEntity(): DiaryEntryEntity {
        return DiaryEntryEntity(
            id = this.id,
            title = this.title,
            content = this.content,
            date = this.date,
            mood = this.mood,
            weather = this.weather,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}
