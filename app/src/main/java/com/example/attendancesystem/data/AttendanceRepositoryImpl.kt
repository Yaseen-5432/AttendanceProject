package com.example.attendancesystem.data

import com.example.attendancesystem.domain.database.dao.AttendanceDao
import com.example.attendancesystem.domain.database.repo.AttendanceRepository
import com.example.attendancesystem.domain.database.tables.AttendanceEntity
import com.example.attendancesystem.domain.model.AttendanceWithStudent
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class AttendanceRepositoryImpl @Inject constructor(
    private val attendanceDao: AttendanceDao
): AttendanceRepository {
    override fun getAttendanceWithStudentBySectionAndDate(
        sectionId: Int,
        date: Long
    ): Flow<List<AttendanceWithStudent>> {
        return attendanceDao.getAttendanceWithStudentBySectionAndDate(sectionId, date)
    }

    override suspend fun markAttendance(attendance: AttendanceEntity) {
        attendanceDao.insertAttendance(attendance)
    }

    override suspend fun insertAttendanceList(attendanceList: List<AttendanceEntity>) {
        attendanceDao.insertAttendanceList(attendanceList)
    }

    override suspend fun deleteAttendanceForStudent(studentId: Int, date: Long) {
        attendanceDao.deleteAttendanceForStudent(studentId,date)
    }

    override fun getAttendanceByStudent(studentId: Int): Flow<List<AttendanceEntity>> {
        return attendanceDao.getAttendanceByStudent(studentId)
    }
    override fun getAttendanceBySectionBetweenDates(
        sectionId: Int,
        start: Long,
        end: Long
    ): Flow<List<AttendanceEntity>> {
        return attendanceDao.getAttendanceBySectionBetweenDates(sectionId, start, end)
    }
    override fun getAttendanceByStudentBetweenDates(
        studentId: Int,
        start: Long,
        end: Long
    ): Flow<List<AttendanceEntity>> {
        return attendanceDao.getAttendanceByStudentBetweenDates(studentId, start, end)
    }

    override fun getMonthlyReport(
        sectionId: Int,
        month: Int
    ): Flow<List<AttendanceEntity>> {
        TODO("Not yet implemented")
    }
}