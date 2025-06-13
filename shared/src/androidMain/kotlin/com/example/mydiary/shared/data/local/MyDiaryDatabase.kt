package com.example.mydiary.shared.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mydiary.shared.data.local.converter.DateTimeConverters
import com.example.mydiary.shared.data.local.dao.DiaryDao
import com.example.mydiary.shared.data.local.model.DiaryEntryEntity

@Database(
    entities = [DiaryEntryEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateTimeConverters::class)
abstract class MyDiaryDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao
}
