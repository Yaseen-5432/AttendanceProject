package com.example.attendancesystem.domain.database.repo

import com.example.attendancesystem.domain.database.tables.AttendanceEntity
import com.example.attendancesystem.domain.model.AttendanceWithStudent
import kotlinx.coroutines.flow.Flow

interface AttendanceRepository {
    fun getAttendanceBySectionAndDate(sectionId: Int, date: Long): Flow<List<AttendanceEntity>>
    suspend fun markAttendance(attendance: AttendanceEntity)
    suspend fun deleteAttendanceForStudent(studentId: Int, date: Long)
    fun getMonthlyReport(sectionId: Int, month: Int): Flow<List<AttendanceEntity>>
    fun getAttendanceByStudent(studentId: Int): Flow<List<AttendanceEntity>>
    fun getAttendanceBySectionBetweenDates(sectionId: Int, start: Long, end: Long): Flow<List<AttendanceEntity>>
    fun getAttendanceWithStudentNames(sectionId: Int, start: Long, end: Long): Flow<List<AttendanceWithStudent>>
}
