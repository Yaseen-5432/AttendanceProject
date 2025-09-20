package com.example.attendancesystem.presentation.screens.student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendancesystem.domain.database.repo.SchoolRepository
import com.example.attendancesystem.domain.database.tables.StudentEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class StudentViewmodel @Inject constructor(
    private val repository: SchoolRepository
): ViewModel() {
    private val _students = MutableStateFlow<List<StudentEntity>>(emptyList())
    val students: StateFlow<List<StudentEntity>> = _students

    private val _selectedStudent = MutableStateFlow<StudentEntity?>(null)
    val selectedStudent: StateFlow<StudentEntity?> = _selectedStudent

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

}