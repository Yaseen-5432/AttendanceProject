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
    SELECT a.attendanceId, s.studentId, s.name, a.date, a.status
    FROM students s
    LEFT JOIN attendance a 
        ON a.studentId = s.studentId 
        AND a.date = :date
    WHERE s.classId = :classId
    ORDER BY s.studentId ASC
""")
    fun getAttendanceWithStudentBySectionAndDatePast(classId: Int, date: Long): Flow<List<AttendanceWithStudent>>

    @Query("""
    SELECT a.attendanceId, s.studentId, s.name, a.date, a.status
    FROM students s
    LEFT JOIN student_history h 
        ON h.studentId = s.studentId 
        AND h.classId = :classId
    LEFT JOIN attendance a 
        ON a.studentId = s.studentId 
        AND a.date = :date
    WHERE s.classId = :classId OR h.classId = :classId
    ORDER BY s.studentId ASC
""")
    fun getAttendanceWithStudentBySectionAndDate(
        classId: Int,
        date: Long
    ): Flow<List<AttendanceWithStudent>>

    // All attendance rows for a student (history)
    @Query("SELECT * FROM attendance WHERE studentId = :studentId ORDER BY date DESC")
    fun getAttendanceByStudent(studentId: Int): Flow<List<AttendanceEntity>>

    // Remove a student's attendance record for a particular date
    @Query("DELETE FROM attendance WHERE studentId = :studentId AND date = :date")
    suspend fun deleteAttendanceForStudent(studentId: Int, date: Long)

    @Query("""
    SELECT a.* FROM attendance a
    INNER JOIN students s ON a.studentId = s.studentId
    WHERE s.classId = :classId AND a.date BETWEEN :start AND :end
    ORDER BY a.date ASC
""")
    fun getAttendanceBySectionBetweenDatesPast(
        classId: Int,
        start: Long,
        end: Long
    ): Flow<List<AttendanceEntity>>


    @Query("""
    SELECT a.*
    FROM attendance a
    INNER JOIN students s ON a.studentId = s.studentId
    WHERE s.classId = :classId
      AND a.date BETWEEN :start AND :end
    ORDER BY a.date ASC
""")
    fun getAttendanceByCurrentClassBetweenDates(
        classId: Int,
        start: Long,
        end: Long
    ): Flow<List<AttendanceEntity>>


    @Query("""
    SELECT * 
    FROM attendance 
    WHERE studentId = :studentId 
      AND date BETWEEN :start AND :end
    ORDER BY date ASC
""")
    fun getAttendanceByStudentBetweenDates(
        studentId: Int,
        start: Long,
        end: Long
    ): Flow<List<AttendanceEntity>>

    @Query("SELECT COUNT(*) FROM attendance")
    suspend fun count(): Int
}
