package com.example.attendancesystem.domain.database.repo

import com.example.attendancesystem.domain.database.tables.ClassEntity
import com.example.attendancesystem.domain.database.tables.StudentEntity
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    fun getClasses(): Flow<List<ClassEntity>>
    suspend fun sessionStart(classId: Int): Long?
    suspend fun getClassById(classId: Int): ClassEntity?
    fun getStudentsByClass(classId: Int): Flow<List<StudentEntity>>
    suspend fun promoteStudent(studentId: Int, newClassId: Int, joinDate: Long)
    suspend fun insertClass(classEntity: ClassEntity)
    suspend fun insertStudent(studentEntity: StudentEntity)
    suspend fun getStudentById(studentId: Int): StudentEntity?
}
