package com.example.attendancesystem.domain.model

import java.time.LocalDate

data class AttendanceWithStudentUI(
    val attendanceId: Int,
    val studentId: Int,
    val name: String,
    val date: LocalDate,
    val status: Int?
)
