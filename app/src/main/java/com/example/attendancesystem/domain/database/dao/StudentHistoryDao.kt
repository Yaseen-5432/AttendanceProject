package com.example.attendancesystem.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.attendancesystem.domain.database.tables.StudentHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: StudentHistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryList(historyList: List<StudentHistoryEntity>)

    @Query("DELETE FROM student_history WHERE historyId = :historyId")
    suspend fun deleteHistoryById(historyId: Int)

    // 🔹 Get all history records for one student
    @Query("""
        SELECT * FROM student_history 
        WHERE studentId = :studentId 
        ORDER BY joinDate ASC
    """)
    fun getHistoryForStudent(studentId: Int): Flow<List<StudentHistoryEntity>>

    // 🔹 Get all students in a past class (only history table, no current students)
    @Query("""
        SELECT * FROM student_history 
        WHERE classId = :classId
        ORDER BY studentId ASC
    """)
    fun getHistoryByClass(classId: Int): Flow<List<StudentHistoryEntity>>

    // 🔹 Check if a student was in a specific class for a year
    @Query("""
        SELECT * FROM student_history 
        WHERE studentId = :studentId AND classId = :classId
        LIMIT 1
    """)
    suspend fun getStudentInClassHistory(studentId: Int, classId: Int): StudentHistoryEntity?


    @Query("""
    SELECT * FROM student_history 
    WHERE studentId = :studentId 
      AND classId = :classId 
      AND joinDate = :joinDate
    LIMIT 1
""")
    suspend fun getStudentHistory(studentId: Int, classId: Int, joinDate: Long): StudentHistoryEntity?

    @Query("SELECT MIN(joinDate) FROM student_history WHERE classId = :classId")
    suspend fun getSessionStartDateForClass(classId: Int): Long?

    @Query("SELECT COUNT(*) FROM student_history")
    suspend fun count(): Int
}
