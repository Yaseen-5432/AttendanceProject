package com.example.attendancesystem.presentation.screens.classes

import android.util.Log
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


    fun loadClasses() {
        Log.d("calculate", "loadClasses: called")
        viewModelScope.launch {
            repository.getClasses().collect { list ->
                _classes.value = list
                Log.d("calculate", "loadClasses: ${list.size}")
            }
        }
    }
    // Insert a new class
    fun insertClass(classEntity: ClassEntity) {
        viewModelScope.launch {
            repository.insertClass(classEntity)
            // Reload classes after inserting to update UI
            loadClasses()
        }
    }


}