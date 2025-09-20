package com.example.attendancesystem.presentation.screens.classes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendancesystem.domain.database.repo.SchoolRepository
import com.example.attendancesystem.domain.database.tables.ClassEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class ClassViewmodel @Inject constructor(
    private val repository: SchoolRepository
): ViewModel() {
    // Selected year state
    private val _selectedYear = MutableStateFlow<Int?>(LocalDate.now().year)
    val selectedYear: StateFlow<Int?> = _selectedYear

    private val _classes = MutableStateFlow<List< ClassEntity>>(emptyList())
    val classes: StateFlow<List<ClassEntity>> = _classes

    // Set the year
    fun setYear(year: Int) {
        _selectedYear.value = year
        loadClassesByYear(year)
    }

    fun loadClassesByYear(year: Int) {
        viewModelScope.launch {
            repository.getClassesForYear(year).collect { list ->
                _classes.value = list
            }
        }
    }
    // Insert a new class
    fun insertClass(classEntity: ClassEntity) {
        viewModelScope.launch {
            repository.insertClass(classEntity)
            // Reload classes after inserting to update UI
            loadClassesByYear(classEntity.year)
        }
    }


}