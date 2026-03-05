package com.example.attendancesystem.domain.database.tables

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

// ClassEntity.kt

@Entity(tableName = "classes",
    indices = [Index(value = ["classId"])]
)
data class ClassEntity(
    @PrimaryKey(autoGenerate = true) val classId: Int = 0,
    val className: String, // e.g., "1", "2", "3" ... "8"
)
