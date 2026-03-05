package com.example.attendancesystem.domain.model

data class AttendanceWithStudent(
    val attendanceId: Int,
    val studentId: Int,
    val name: String,
    val date: Long,
    val status: Int?
)

