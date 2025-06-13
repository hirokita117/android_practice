package com.example.mydiary.shared.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mydiary.shared.data.local.model.DiaryEntryEntity
import kotlinx.datetime.LocalDate

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary_entries WHERE strftime('%m', date) = :month AND strftime('%Y', date) = :year ORDER BY date DESC")
    suspend fun getDiaryEntries(month: String, year: String): List<DiaryEntryEntity>

    @Query("SELECT * FROM diary_entries WHERE id = :id")
    suspend fun getDiaryEntry(id: String): DiaryEntryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiaryEntry(entry: DiaryEntryEntity)

    @Update
    suspend fun updateDiaryEntry(entry: DiaryEntryEntity)

    @Query("DELETE FROM diary_entries WHERE id = :id")
    suspend fun deleteDiaryEntry(id: String)

    @Query("SELECT * FROM diary_entries WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    suspend fun getEntriesByDateRange(startDate: LocalDate, endDate: LocalDate): List<DiaryEntryEntity>
}
