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
    @Query("SELECT * FROM students WHERE sectionId = :sectionId ORDER BY name ASC")
    fun getStudentsBySection(sectionId: Int): Flow<List<StudentEntity>>

    // Get a specific student by ID
    @Query("SELECT * FROM students WHERE studentId = :id LIMIT 1")
    suspend fun getStudentById(id: Int): StudentEntity?

    // Get all students (useful for admin view)
    @Query("SELECT * FROM students ORDER BY name ASC")
    fun getAllStudents(): Flow<List<StudentEntity>>

    // Promote student: update sectionId (just call this in code)
    @Query("UPDATE students SET sectionId = :newSectionId WHERE studentId = :studentId")
    suspend fun promoteStudent(studentId: Int, newSectionId: Int)
}
