package com.example.attendancesystem.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.attendancesystem.domain.database.tables.AttendanceEntity
import com.example.attendancesystem.domain.model.AttendanceWithStudent
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendanceEntity: AttendanceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendanceList(attendanceList: List<AttendanceEntity>)

    @Update
    suspend fun updateAttendance(attendanceEntity: AttendanceEntity)

    @Delete
    suspend fun deleteAttendance(attendanceEntity: AttendanceEntity)

    // Get attendance for a specific student
    @Query("SELECT * FROM attendance WHERE studentId = :studentId ORDER BY date DESC")
    fun getAttendanceForStudent(studentId: Int): Flow<List<AttendanceEntity>>

    // Get attendance for all students in a section on a specific date
    @Query("""
        SELECT a.* FROM attendance a
        INNER JOIN students s ON a.studentId = s.studentId
        WHERE s.sectionId = :sectionId AND a.date = :date
        ORDER BY s.name ASC
    """)
    fun getAttendanceBySectionAndDate(sectionId: Int, date: Long): Flow<List<AttendanceEntity>>

    // All attendance rows for a student (history)
    @Query("SELECT * FROM attendance WHERE studentId = :studentId ORDER BY date DESC")
    fun getAttendanceByStudent(studentId: Int): Flow<List<AttendanceEntity>>

    // Remove a student's attendance record for a particular date
    @Query("DELETE FROM attendance WHERE studentId = :studentId AND date = :date")
    suspend fun deleteAttendanceForStudent(studentId: Int, date: Long)

    @Query("""
    SELECT a.* FROM attendance a
    INNER JOIN students s ON a.studentId = s.studentId
    WHERE s.sectionId = :sectionId AND a.date BETWEEN :start AND :end
    ORDER BY a.date ASC
""")
    fun getAttendanceBySectionBetweenDates(
        sectionId: Int,
        start: Long,
        end: Long
    ): Flow<List<AttendanceEntity>>

    @Query("""
    SELECT s.studentId, s.name, a.date, a.status
    FROM attendance a
    INNER JOIN students s ON a.studentId = s.studentId
    WHERE s.sectionId = :sectionId AND a.date BETWEEN :start AND :end
    ORDER BY a.date ASC
""")
    fun getAttendanceWithStudentNames(
        sectionId: Int,
        start: Long,
        end: Long
    ): Flow<List<AttendanceWithStudent>>


}
