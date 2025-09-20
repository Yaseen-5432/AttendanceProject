package com.example.attendancesystem.domain.database.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// StudentEntity.kt

@Entity(tableName = "students",
    indices = [Index(value = ["classId"])])
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) val studentId: Int = 0,
    val name: String,
    val fatherName: String,
    val phoneNumber: String,
    val classId: Int // always points to the student's *current* class
)


