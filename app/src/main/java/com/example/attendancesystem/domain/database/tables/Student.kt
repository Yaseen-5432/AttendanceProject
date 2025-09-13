package com.example.attendancesystem.domain.database.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// StudentEntity.kt

@Entity(
    tableName = "students",
    foreignKeys = [
        ForeignKey(
            entity = SectionEntity::class,
            parentColumns = ["sectionId"],
            childColumns = ["sectionId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index(value = ["sectionId"])]
)
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) val studentId: Int = 0,
    val name: String,
    val fatherName: String,
    val phone: String?,
    val sectionId: Int?, // can be NULL if student is not assigned a class/section yet
)


