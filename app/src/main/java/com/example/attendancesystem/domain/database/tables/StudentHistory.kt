package com.example.attendancesystem.domain.database.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "student_history",
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["studentId"],
            childColumns = ["studentId"]
        ),
        ForeignKey(entity = ClassEntity::class, parentColumns = ["classId"], childColumns = ["classId"])
    ],
    indices = [Index(value = ["studentId"]), Index(value = ["classId"]), Index(value = ["year"])]
)
data class StudentHistoryEntity(
    @PrimaryKey(autoGenerate = true) val historyId: Int = 0,
    val studentId: Int,
    val classId: Int,
    val year: Int // academic year of that old class
)

