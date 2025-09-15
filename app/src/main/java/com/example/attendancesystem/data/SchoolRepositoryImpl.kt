package com.example.attendancesystem.data

import com.example.attendancesystem.domain.database.dao.ClassDao
import com.example.attendancesystem.domain.database.dao.SectionDao
import com.example.attendancesystem.domain.database.dao.StudentDao
import com.example.attendancesystem.domain.database.repo.SchoolRepository
import com.example.attendancesystem.domain.database.tables.ClassEntity
import com.example.attendancesystem.domain.database.tables.SectionEntity
import com.example.attendancesystem.domain.database.tables.StudentEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class SchoolRepositoryImpl @Inject constructor(
    private val classDao: ClassDao,
    private val sectionDao: SectionDao,
    private val studentDao: StudentDao
): SchoolRepository {
    override fun getSectionsForYear(year: Int): Flow<List<SectionEntity>> {
        return sectionDao.getSectionsForYear(year)
    }

    override suspend fun getClassById(classId: Int): ClassEntity? {
        return classDao.getClassById(classId)
    }

    override fun getStudentsBySection(sectionId: Int): Flow<List<StudentEntity>> {
        return studentDao.getStudentsBySection(sectionId)
    }

    override suspend fun promoteStudent(studentId: Int, newSectionId: Int) {
        studentDao.promoteStudent(studentId, newSectionId)
    }

    override suspend fun insertClass(classEntity: ClassEntity) {
        classDao.insertClass(classEntity)
    }

    override suspend fun insertSection(sectionEntity: SectionEntity) {
        sectionDao.insertSection(sectionEntity)
    }

    override suspend fun insertStudent(studentEntity: StudentEntity) {
        studentDao.insertStudent(studentEntity)
    }
}