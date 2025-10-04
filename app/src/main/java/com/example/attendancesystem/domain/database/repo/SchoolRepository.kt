package com.example.attendancesystem.domain.database.repo

import com.example.attendancesystem.domain.database.tables.ClassEntity
import com.example.attendancesystem.domain.database.tables.StudentEntity
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    fun getClassesForYear(year: Int): Flow<List<ClassEntity>>
    suspend fun getClassById(classId: Int): ClassEntity?
    fun getStudentsByClass(classId: Int): Flow<List<StudentEntity>>
    suspend fun promoteStudent(studentId: Int, newClassId: Int)
    suspend fun insertClass(classEntity: ClassEntity)
    suspend fun insertStudent(studentEntity: StudentEntity)
}
