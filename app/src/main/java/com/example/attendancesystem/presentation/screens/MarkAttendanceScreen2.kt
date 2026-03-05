package com.example.attendancesystem.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.attendancesystem.presentation.component.*
import com.example.attendancesystem.presentation.screens.attendance.AttendanceViewmodel
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun MarkAttendanceScreen2(
    navController: NavController,
    classId: Int,
    modifier: Modifier = Modifier,
    viewModel: AttendanceViewmodel = hiltViewModel()
) {
    // Observe ViewModel states (exact names as in your ViewModel)
    val studentList by viewModel.attendanceWithStudent.collectAsState()
    val currentMonth by viewModel.currentMonth.collectAsState()   // YearMonth
    val selectedDate by viewModel.selectedDate.collectAsState()   // LocalDate?
    val className by viewModel.className.collectAsState()
    val weekDays by viewModel.weekDays.collectAsState()
    //val percentage by viewModel.studentAttendancePercentage.collectAsState() // Float

    // format percentage to string because your ClassPercentage() takes String
   // val percentageText = "${percentage.toInt()}%"
    // current month and date selector
    LaunchedEffect(classId) {
        viewModel.setMonth(YearMonth.now())
        viewModel.onDateSelected(LocalDate.now(), classId)
        viewModel.loadClassName(classId)
    }

    // Error handling: if classId is 0, show an error message and prevent further actions
    if (classId == 0) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text("Error: Invalid class selected. Please go back and select a valid class.", color = Color.Red)
        }
        return
    }

    Scaffold(
        modifier = modifier,
        topBar = { TopBar(title = "Mark Attendance") }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SecondBar(
                title = className,
                buttonText = "Submit"
            ) {
                // Submit needs a LocalDate param (viewModel.submitAttendance(date: LocalDate))
                selectedDate?.let { safeDate ->
                    viewModel.submitAttendance(safeDate)
                    navController.navigate("markAttendanceScreen1")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    // Header: month/date/percentage
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .border(2.dp, Color.Black),
                        verticalArrangement = Arrangement.Center
                    ) {
                        // ---- Month selector (use exact params your component expects) ----


                        // ---- Date selector (notify VM with classId) ----
                        DateSelector(
                            selectedDate = selectedDate,
                            daysdate = weekDays.map { it.dayOfMonth.toString() },
                            onDateSelected = { date ->
                                viewModel.onDateSelected(date, classId)
                            }
                        )

                    }
                }

                // ---- Students list from ViewModel ----
                items(studentList) { student ->
                    Spacer(modifier = Modifier.height(10.dp))

                    // AttendanceCard must accept isPresent:Boolean and a callback
                    AttendanceCard(
                        name = student.name,
                        rollNo = student.studentId.toString(),
                        status = student.status,
                        onStatusChange = { newStatusInt ->
                            viewModel.markAttendance(student.studentId, newStatusInt)
                        }
                    )
                }
            }
        }
    }
}
