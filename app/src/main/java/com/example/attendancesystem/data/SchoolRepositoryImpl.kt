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
    override fun getClasses(): Flow<List<ClassEntity>> {
        return classDao.getAllClasses()
    }

    override suspend fun sessionStart(classId: Int): Long? {
        return studentHistoryDao.getSessionStartDateForClass(classId)
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
        val history = StudentHistoryEntity(
            studentId = studentEntity.studentId,
            classId = studentEntity.classId,
            joinDate = LocalDate.now().toEpochDay(),
        )
        studentHistoryDao.insertHistory(history)
    }

    override suspend fun getStudentById(studentId: Int): StudentEntity? {
        return studentDao.getStudentById(studentId)
    }

    override suspend fun promoteStudent(studentId: Int, newClassId: Int, joinDate: Long) {
        // 1. Update student's current class
        studentDao.promoteStudent(studentId, newClassId)

        // 2. Get new class info
        val newClass = classDao.getClassById(newClassId) ?: return

        // 3. Check if history already exists for this student in this class & year
        val existingHistory = studentHistoryDao.getStudentHistory(studentId, newClassId, joinDate)

        if (existingHistory == null) {
            // 4. Insert only if no duplicate exists
            val newHistory = StudentHistoryEntity(
                studentId = studentId,
                classId = newClassId,
                joinDate = joinDate
            )
            studentHistoryDao.insertHistory(newHistory)
        }
    }

}