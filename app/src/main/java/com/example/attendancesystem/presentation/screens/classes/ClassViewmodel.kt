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

    // ✅ Selected year store karne ke liye
    private val _selectedYear = MutableStateFlow<Int?>(LocalDate.now().year)
    val selectedYear: StateFlow<Int?> = _selectedYear

    // ✅ UI ke liye classes ka list state
    private val _classes = MutableStateFlow<List<ClassEntity>>(emptyList())
    val classes: StateFlow<List<ClassEntity>> = _classes

    // ✅ Year set karna aur uske hisaab se classes load karna
    fun setYear(year: Int) {
        _selectedYear.value = year
        loadClassesByYear(year)
    }

    // ✅ Sirf ek particular year ki classes load karna
     fun loadClassesByYear(year: Int) {
        viewModelScope.launch {
            repository.getClassesForYear(year).collect { list ->
                println("DEBUG: Classes for $year loaded = ${list.size}") // 🔍 Debug log
                _classes.value = list
            }
        }
    }

    // ✅ Saari classes bina year filter ke load karna
    fun loadAllClasses() {
        viewModelScope.launch {
            repository.getAllClasses().collect { list ->
                println("DEBUG: All classes loaded = ${list.size}") // 🔍 Debug log
                _classes.value = list
            }
        }
    }

    // ✅ Nai class insert karna aur refresh karna
    fun insertClass(classEntity: ClassEntity) {
        viewModelScope.launch {
            repository.insertClass(classEntity)
            // Insert ke baad usi year ki classes reload karenge
            loadClassesByYear(classEntity.year)
        }
    }

}
