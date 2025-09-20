package com.example.attendancesystem.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.attendancesystem.domain.database.tables.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(studentEntity: StudentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudents(students: List<StudentEntity>)

    @Update
    suspend fun updateStudent(studentEntity: StudentEntity)

    @Delete
    suspend fun deleteStudent(studentEntity: StudentEntity)

    // Get all students of a section
    @Query("SELECT * FROM students WHERE classId = :classId ORDER BY studentId ASC")
    fun getStudentsByClassPast(classId: Int): Flow<List<StudentEntity>>

    // Get a specific student by ID
    @Query("SELECT * FROM students WHERE studentId = :id LIMIT 1")
    suspend fun getStudentById(id: Int): StudentEntity?

    // Get all students (useful for admin view)
    @Query("SELECT * FROM students ORDER BY name ASC")
    fun getAllStudents(): Flow<List<StudentEntity>>
    @Query("""
    SELECT * FROM students 
    WHERE studentId IN (
        SELECT studentId FROM student_history 
        WHERE classId = :classId
    )
    ORDER BY studentId ASC
""")
    fun getStudentsByClass(classId: Int): Flow<List<StudentEntity>>

    // Promote student: update sectionId (just call this in code)
    @Query("UPDATE students SET classId = :newClassId WHERE studentId = :studentId")
    suspend fun promoteStudent(studentId: Int, newClassId: Int)
}
