package com.example.attendancesystem.domain.database.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// AttendanceEntity.kt


@Entity(
    tableName = "attendance",
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["studentId"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["studentId"]),  Index(value = ["date"])]
)
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true) val attendanceId: Int = 0,
    val studentId: Int,
    val date: Long,  // store as timestamp (easy filtering by date)
    val status: Int?  // 0 = Present, 1 = Absent, 2 = Leave
)


