package com.example.attendancesystem.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.attendancesystem.presentation.component.Callendar
import com.example.attendancesystem.presentation.component.CustomButton
import com.example.attendancesystem.presentation.component.SecondBar
import com.example.attendancesystem.presentation.component.TopBar
import com.example.attendancesystem.presentation.screens.attendance.AttendanceViewmodel

@Composable
fun ViewAttendanceScreen2(
    navController: NavController,
    classId: Int,
    modifier: Modifier = Modifier,
    viewModel: AttendanceViewmodel = hiltViewModel()
) {
    // Observe data
    val studentList by viewModel.attendanceWithStudent.collectAsState()
    val selectedDate by viewModel.selectedDate.collectAsState()
    val days by viewModel.days.collectAsState()

    // Load attendance when screen opens
    LaunchedEffect(classId, selectedDate) {
        viewModel.loadAttendanceForStudent(classId, selectedDate ?: java.time.LocalDate.now())
    }

    Scaffold(
        modifier = modifier,
        topBar = { TopBar(title = "View Attendance") }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SecondBar(
                title = "Class $classId", // dynamically show classId or name
            ) { /* future actions */ }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                // Calendar as a single item


                // Student list as separate items
                items(studentList.size) { index ->
                    val student = studentList[index]
                    Spacer(modifier = Modifier.height(10.dp))
                    CustomButton(
                        text = student.name,
                        onClick = {
                            navController.navigate("attendanceScreen/${student.studentId}/$classId")
                        },
                    )
                }
            }
        }
    }
}
