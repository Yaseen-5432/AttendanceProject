package com.example.attendancesystem.presentation.screens.attendance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendancesystem.domain.database.converter.toEpochMillis
import com.example.attendancesystem.domain.database.converter.toLocalDate
import com.example.attendancesystem.domain.database.repo.AttendanceRepository
import com.example.attendancesystem.domain.database.tables.AttendanceEntity
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
    private val repository: AttendanceRepository
): ViewModel() {
    private val _studentAttendancePercentage = MutableStateFlow(0f)
    val studentAttendancePercentage: StateFlow<Float> = _studentAttendancePercentage

    private val _selectedDate = MutableStateFlow<LocalDate?>(LocalDate.now())
    val selectedDate: StateFlow<LocalDate?> = _selectedDate


    private val _attendanceWithStudent = MutableStateFlow<List<AttendanceWithStudentUI>>(emptyList())
    val attendanceWithStudent: StateFlow<List<AttendanceWithStudentUI>> = _attendanceWithStudent

    private val _currentMonth = MutableStateFlow(YearMonth.now())
    val currentMonth: StateFlow<YearMonth> = _currentMonth

    private val _weekDays = MutableStateFlow<List<LocalDate>>(emptyList())
    val weekDays: StateFlow<List<LocalDate>> = _weekDays

    val days: StateFlow<List<LocalDate>> = _currentMonth
        .map { ym -> getDaysOfMonth(ym) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())


    init {
        // Initialize weekDays with the current week based on today
        _weekDays.value = getWeekDates(LocalDate.now())
    }


    fun onDateSelected(date: LocalDate, classId: Int) {
        _selectedDate.value = date
        updateWeekDays(date)
        loadAttendanceForStudent(classId, date)
    }

    fun loadAttendanceForStudent(classId: Int, date: LocalDate) {
        viewModelScope.launch {
            repository.getAttendanceWithStudentBySectionAndDate(
                classId, date.toEpochMillis()
            ).collect { list ->
                _attendanceWithStudent.value = list.map { item ->
                    AttendanceWithStudentUI(
                        attendanceId = item.attendanceId,
                        studentId = item.studentId,
                        name = item.name,
                        date = item.date.toLocalDate(), // convert here ✅
                        status = item.status
                    )
                }
            }
        }
    }

    fun markAttendance(studentId: Int, newStatus: Int) {
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



    fun loadStudentAttendancePercentage(studentId: Int, start: Long, end: Long) {
        viewModelScope.launch {
            repository.getAttendanceByStudentBetweenDates(studentId, start, end)
                .collect { attendanceList ->
                    if (attendanceList.isEmpty()) {
                        _studentAttendancePercentage.value = 0f
                    } else {
                        val total = attendanceList.size
                        val present = attendanceList.count { it.status == 0 }
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


    private fun updateWeekDays(date: LocalDate) {
        _weekDays.value = getWeekDates(date)
    }
    fun loadClassAttendancePercentage(classId: Int, date: LocalDate) {
        viewModelScope.launch {
            repository.getAttendanceByClassOnDate(classId, date.toEpochMillis())
                .collect { attendanceList ->
                    if (attendanceList.isEmpty()) {
                        _studentAttendancePercentage.value = 0f
                    } else {
                        val total = attendanceList.size
                        val present = attendanceList.count { it.status == 0 }
                        _studentAttendancePercentage.value = (present.toFloat() / total.toFloat()) * 100f
                    }
                }
        }
    }





}