package com.example.attendancesystem.domain.database.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sections",
    foreignKeys = [
        ForeignKey(
            entity = ClassEntity::class,
            parentColumns = ["classId"],
            childColumns = ["classId"],
            onDelete = ForeignKey.CASCADE // delete sections if class is deleted (rare case)
        )
    ],
    indices = [Index(value = ["classId"])]
)
data class SectionEntity(
    @PrimaryKey(autoGenerate = true) val sectionId: Int = 0,
    val classId: Int,      // FK → ClassEntity
    val sectionName: String?,  // e.g., "A", "B"
    val year: Int          // e.g., 2024 → so we can know which sections existed in which year
)

