package com.example.attendancesystem.presentation.screens.attendance

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendancesystem.domain.database.converter.toEpochMillis
import com.example.attendancesystem.domain.database.converter.toLocalDate
import com.example.attendancesystem.domain.database.repo.AttendanceRepository
import com.example.attendancesystem.domain.database.repo.SchoolRepository
import com.example.attendancesystem.domain.database.tables.AttendanceEntity
import com.example.attendancesystem.domain.database.tables.StudentEntity
import com.example.attendancesystem.domain.model.AttendanceWithStudent
import com.example.attendancesystem.domain.model.AttendanceWithStudentUI
import dagger.hilt.android.lifecycle.HiltViewModel
//import jakarta.inject.Inject
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

@HiltViewModel
class AttendanceViewmodel @Inject constructor(
    private val repository: AttendanceRepository,
    private val schoolrepo: SchoolRepository
): ViewModel() {
    private val _students = MutableStateFlow<List<StudentEntity>>(emptyList())
    val students: StateFlow<List<StudentEntity>> = _students

    private val _sessionStartDate = MutableStateFlow<LocalDate?>(null)
    val sessionStartDate: StateFlow<LocalDate?> = _sessionStartDate

    private val _studentAttendancePercentage = MutableStateFlow(0f)
    val studentAttendancePercentage: StateFlow<Float> = _studentAttendancePercentage

    private val _studentName = MutableStateFlow("")
    val studentName: StateFlow<String> = _studentName

    private val _selectedDate = MutableStateFlow<LocalDate?>(LocalDate.now())
    val selectedDate: StateFlow<LocalDate?> = _selectedDate


    private val _attendanceWithStudent = MutableStateFlow<List<AttendanceWithStudent>>(emptyList())
    val attendanceWithStudent: StateFlow<List<AttendanceWithStudent>> = _attendanceWithStudent

    private val _currentMonth = MutableStateFlow(YearMonth.now())
    val currentMonth: StateFlow<YearMonth> = _currentMonth

    private val _className = MutableStateFlow("")
    val className: StateFlow<String> = _className

    private val _weekDays = MutableStateFlow<List<LocalDate>>(emptyList())
    val weekDays: StateFlow<List<LocalDate>> = _weekDays

    val days: StateFlow<List<LocalDate>> = _currentMonth
        .map { ym -> getDaysOfMonth(ym) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())


    init {
        // Initialize weekDays with the current week based on today
        _weekDays.value = getWeekDates(LocalDate.now())
    }


    fun loadClassName(classId: Int) {
        if (classId != null || classId != 0) {
            viewModelScope.launch {
                val classEntity = schoolrepo.getClassById(classId)
                _className.value = classEntity?.className ?: "Unknown Class"
            }
        }
    }

    fun loadStudentName(studentId: Int) {
        if (studentId != null || studentId != 0) {
            viewModelScope.launch {
                val studentEntity = schoolrepo.getStudentById(studentId)
                _studentName.value =  studentEntity?.name ?: "Unknown Student"
            }
        }
    }

    fun loadStudentsByClass(classId: Int) {
        if (classId != null || classId != 0) {
            viewModelScope.launch {
                schoolrepo.getStudentsByClass(classId).collect { list ->
                    _students.value = list
                }
            }
        }
    }


    fun onDateSelected(date: LocalDate, classId: Int) {
        if (classId != null && classId != 0) {
            _selectedDate.value = date
            updateWeekDays(date)
            loadAttendanceForStudent(classId, date)
        }
    }

    fun loadAttendanceForStudent(classId: Int, date: LocalDate) {
        viewModelScope.launch {
            repository.getAttendanceWithStudentBySectionAndDate(
                classId, date.toEpochMillis()
            ).collect { list ->
                _attendanceWithStudent.value = list
            }
        }
    }

    fun markAttendance(studentId: Int, newStatus: Int?) {
        _attendanceWithStudent.value = _attendanceWithStudent.value.map { item ->
            if (item.studentId == studentId) {
                // update status, keep everything else the same
                item.copy(status = newStatus)
            } else {
                item
            }
        }
    }
    fun submitAttendance(date: LocalDate) {
        val dateconverted = date.toEpochMillis()
        viewModelScope.launch {
            val attendanceEntities = _attendanceWithStudent.value.map { item ->
                AttendanceEntity(
                    attendanceId = item.attendanceId,
                    studentId = item.studentId,
                    date = dateconverted,
                    status = item.status
                )
            }
            repository.insertAttendanceList(attendanceEntities)
        }
    }



    fun loadStudentAttendancePercentage(studentId: Int, start: LocalDate, end: LocalDate) {
        viewModelScope.launch {
            repository.getAttendanceByStudentBetweenDates(studentId, start.toEpochMillis(), end.toEpochMillis())
                .collect { attendanceList ->
                    if (attendanceList.isEmpty()) {
                        _studentAttendancePercentage.value = 0f
                    } else {
                        val total = attendanceList.size
                        val present = attendanceList.count { it.status == 1 }
                        _studentAttendancePercentage.value =
                            (present.toFloat() / total.toFloat()) * 100f
                    }
                }
        }
    }

    fun nextMonth() {
        _currentMonth.value = _currentMonth.value.plusMonths(1)
    }

    fun previousMonth() {
        _currentMonth.value = _currentMonth.value.minusMonths(1)
    }

    fun setMonth(yearMonth: YearMonth) {
        _currentMonth.value = yearMonth
    }



    fun getWeekDates(today: LocalDate = LocalDate.now()): List<LocalDate> {
        val start = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY))
        return List(7) { index ->
            start.plusDays(index.toLong())
        }
    }

    fun getDaysOfMonth(yearMonth: YearMonth): List<LocalDate> {
        val firstDayOfMonth = yearMonth.atDay(1)
        val start = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY))
        return List(42) { index -> start.plusDays(index.toLong()) } // 6 weeks grid
    }

    fun loadSessionStartDate(classId: Int) {
        viewModelScope.launch {
            val session = schoolrepo.sessionStart(classId)
            _sessionStartDate.value = session?.toLocalDate()
        }
    }


    private fun updateWeekDays(date: LocalDate) {
        _weekDays.value = getWeekDates(date)
    }






}