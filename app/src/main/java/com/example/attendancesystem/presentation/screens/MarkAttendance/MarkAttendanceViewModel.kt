package com.example.attendancesystem.presentation.screens.MarkAttendance

import androidx.lifecycle.ViewModel
import com.example.attendancesystem.domain.database.repo.AttendanceRepository
import com.example.attendancesystem.domain.database.repo.SchoolRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class MarkAttendanceViewModel @Inject constructor(
    private val attendanceRepository: AttendanceRepository,
    private val schoolRepository: SchoolRepository
) : ViewModel() {


}