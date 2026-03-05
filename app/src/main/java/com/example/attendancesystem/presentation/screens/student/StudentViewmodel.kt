package com.example.attendancesystem.presentation.screens.student

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendancesystem.domain.database.converter.toEpochMillis
import com.example.attendancesystem.domain.database.converter.toLocalDate
import com.example.attendancesystem.domain.database.dao.AttendanceDao
import com.example.attendancesystem.domain.database.repo.SchoolRepository
import com.example.attendancesystem.domain.database.tables.StudentEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

@HiltViewModel
class StudentViewmodel @Inject constructor(
    private val repository: SchoolRepository,
    private val attnedanceDao: AttendanceDao
): ViewModel() {
    private val _studentAttendancePercentage = MutableStateFlow(0f)
    val studentAttendancePercentage: StateFlow<Float> = _studentAttendancePercentage

    private val _sessionStartDate = MutableStateFlow<LocalDate?>(null)
    val sessionStartDate: StateFlow<LocalDate?> = _sessionStartDate

    private val _students = MutableStateFlow<List<StudentEntity>>(emptyList())
    val students: StateFlow<List<StudentEntity>> = _students

    private val _selectedMonth = MutableStateFlow<YearMonth?>(null)
    val selectedMonth: StateFlow<YearMonth?> = _selectedMonth

    private val _selectedStudent = MutableStateFlow<StudentEntity?>(null)
    val selectedStudent: StateFlow<StudentEntity?> = _selectedStudent

    private val _className = MutableStateFlow("")
    val className: StateFlow<String> = _className

    fun loadClassName(classId: Int) {
        if (classId != null || classId != 0) {
            viewModelScope.launch {
                val classEntity = repository.getClassById(classId)
                _className.value = classEntity?.className ?: "Unknown Class"
            }
        }
    }

    private var currentClassId: Int? = null


    fun loadStudentsByClass(classId: Int) {
        currentClassId = classId
        viewModelScope.launch {
            repository.getStudentsByClass(classId).collect { list ->
                _students.value = list
            }
        }
    }

    fun selectStudent(studentId: Int) {
        _selectedStudent.value = _students.value.find { it.studentId == studentId }
    }

    fun insertStudent(student: StudentEntity) {
        viewModelScope.launch {
            repository.insertStudent(student)
            // Reload students for the current class to update UI
            currentClassId?.let { loadStudentsByClass(it) }
        }
    }

    fun setSelectedMonth(classId: Int, yearMonth: YearMonth?) {
        _selectedMonth.value = yearMonth
        loadClassAttendancePercentage(classId, yearMonth?.atDay(1), yearMonth?.atEndOfMonth() ?: LocalDate.now())

    }

    fun loadClassAttendancePercentage(classId: Int, start: LocalDate?, end: LocalDate) {
        viewModelScope.launch {
            Log.d("calculate", "loadClassAttendancePercentage: called")
            attnedanceDao.getAttendanceByCurrentClassBetweenDates(classId, start?.toEpochMillis() ?: LocalDate.now().toEpochMillis(), end.toEpochMillis())
                .collect { attendanceList ->
                    Log.d("calculate", "loadClassAttendancePercentage: ${attendanceList.size}")
                    if (attendanceList.isEmpty()) {
                        _studentAttendancePercentage.value = 0f
                    } else {
                        val total = attendanceList.size
                        val present = attendanceList.count { it.status == 1 }
                        Log.d("calculate", "loadClassAttendancePercentage: total=$total, present=$present")
                        _studentAttendancePercentage.value = (present.toFloat() / total.toFloat()) * 100f

                    }
                }
        }
    }

    fun loadSessionAndAttendance(classId: Int) {
        viewModelScope.launch {
            // 1️⃣ First: Load session start date
            val session = repository.sessionStart(classId)
            val sessionDate = session?.toLocalDate()
            _sessionStartDate.value = sessionDate
            Log.d("calculate", "Session start: $sessionDate")

            // 2️⃣ Then: Load attendance percentage only after session date is loaded
            if (sessionDate != null) {
                loadClassAttendancePercentage(classId, sessionDate, LocalDate.now())
            }
        }
    }


}