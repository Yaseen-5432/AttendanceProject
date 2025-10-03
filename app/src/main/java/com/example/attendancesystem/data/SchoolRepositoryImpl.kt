package com.example.attendancesystem.data

import com.example.attendancesystem.domain.database.dao.ClassDao
import com.example.attendancesystem.domain.database.dao.StudentDao
import com.example.attendancesystem.domain.database.dao.StudentHistoryDao
import com.example.attendancesystem.domain.database.repo.SchoolRepository
import com.example.attendancesystem.domain.database.tables.ClassEntity
import com.example.attendancesystem.domain.database.tables.StudentEntity
import com.example.attendancesystem.domain.database.tables.StudentHistoryEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class SchoolRepositoryImpl @Inject constructor(
    private val classDao: ClassDao,
    private val studentDao: StudentDao,
    private val studentHistoryDao: StudentHistoryDao
): SchoolRepository {
    override fun getClassesForYear(year: Int): Flow<List<ClassEntity>> {
        return classDao.getClassesForYear(year)
    }

    override suspend fun getClassById(classId: Int): ClassEntity? {
        return classDao.getClassById(classId)
    }

    override fun getStudentsByClass(classId: Int): Flow<List<StudentEntity>> {
        return studentDao.getStudentsByClass(classId)
    }


    override suspend fun insertClass(classEntity: ClassEntity) {
        classDao.insertClass(classEntity)
    }

    override suspend fun insertStudent(studentEntity: StudentEntity) {
        studentDao.insertStudent(studentEntity)
        val classEntity = classDao.getClassById(studentEntity.classId!!)
        val history = StudentHistoryEntity(
            studentId = studentEntity.studentId,
            classId = studentEntity.classId,
            year = classEntity?.year ?: LocalDate.now().year,
        )
        studentHistoryDao.insertHistory(history)
    }

    override suspend fun promoteStudent(studentId: Int, newClassId: Int) {
        // 1. Update student's current class
        studentDao.promoteStudent(studentId, newClassId)

        // 2. Get new class info
        val newClass = classDao.getClassById(newClassId) ?: return

        // 3. Check if history already exists for this student in this class & year
        val existingHistory = studentHistoryDao.getStudentHistory(studentId, newClassId, newClass.year)

        if (existingHistory == null) {
            // 4. Insert only if no duplicate exists
            val newHistory = StudentHistoryEntity(
                studentId = studentId,
                classId = newClassId,
                year = newClass.year
            )
            studentHistoryDao.insertHistory(newHistory)
        }
    }
    override fun getAllClasses(): Flow<List<ClassEntity>> {
        return classDao.getAllClasses()
    }


}