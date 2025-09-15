package com.example.attendancesystem.domain.database.repo

import com.example.attendancesystem.domain.database.tables.ClassEntity
import com.example.attendancesystem.domain.database.tables.SectionEntity
import com.example.attendancesystem.domain.database.tables.StudentEntity
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    fun getSectionsForYear(year: Int): Flow<List<SectionEntity>>
    suspend fun getClassById(classId: Int): ClassEntity?
    fun getStudentsBySection(sectionId: Int): Flow<List<StudentEntity>>
    suspend fun promoteStudent(studentId: Int, newSectionId: Int)
    suspend fun insertClass(classEntity: ClassEntity)
    suspend fun insertSection(sectionEntity: SectionEntity)
    suspend fun insertStudent(studentEntity: StudentEntity)
}
