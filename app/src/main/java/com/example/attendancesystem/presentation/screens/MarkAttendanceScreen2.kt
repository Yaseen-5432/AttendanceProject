//package com.example.attendancesystem.presentation.screens
//
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.example.attendancesystem.presentation.component.*
//import com.example.attendancesystem.presentation.screens.attendance.AttendanceViewmodel
//import java.time.YearMonth
//
//@Composable
//fun MarkAttendanceScreen2(
//    navController: NavController,
//    classId: Int,
//    modifier: Modifier = Modifier,
//    viewModel: AttendanceViewmodel = hiltViewModel()
//) {
//    // Observe ViewModel states (exact names as in your ViewModel)
//    val studentList by viewModel.attendanceWithStudent.collectAsState()
//    val currentMonth by viewModel.currentMonth.collectAsState()   // YearMonth
//    val selectedDate by viewModel.selectedDate.collectAsState()   // LocalDate?
//    val percentage by viewModel.studentAttendancePercentage.collectAsState() // Float
//
//    // format percentage to string because your ClassPercentage() takes String
//    val percentageText = "${percentage.toInt()}%"
//    // current month and date selector
//    LaunchedEffect(classId) {
//        viewModel.setMonth(java.time.YearMonth.now())
//        viewModel.onDateSelected(java.time.LocalDate.now(), classId)
//    }
//
//
//    Scaffold(
//        modifier = modifier,
//        topBar = { TopBar(title = "Mark Attendance") }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ) {
//            SecondBar(
//                title = "Class $classId",
//                buttonText = "Submit"
//            ) {
//                // Submit needs a LocalDate param (viewModel.submitAttendance(date: LocalDate))
//                selectedDate?.let { safeDate ->
//                    viewModel.submitAttendance(safeDate)
//                    navController.navigate("attendanceSummary")
//                }
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            LazyColumn(modifier = Modifier.fillMaxSize()) {
//                item {
//                    // Header: month/date/percentage
//                    Column(
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .fillMaxWidth()
//                            .border(2.dp, Color.Black),
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        // ---- Month selector (use exact params your component expects) ----
//                        MonthSelector(
//                            currentMonth = currentMonth,                 // YearMonth from VM
//                            onPrevious = { viewModel.previousMonth() },  // call VM function
//                            onNext = { viewModel.nextMonth() },          // call VM function
//                            onMonthSelected = { ym: YearMonth -> viewModel.setMonth(ym) }
//                        )
//
//                        // ---- Date selector (notify VM with classId) ----
//                        DateSelector(
//                            selectedDate = selectedDate,
//                            onDateSelected = { date ->
//                                // This updates selectedDate in VM and loads attendance for classId
//                                viewModel.onDateSelected(date, classId)
//                            }
//                        )
//
//                        // ---- Class percentage (your component expects String) ----
//                        ClassPercentage(percentage = percentageText)
//                    }
//                }
//
//                // ---- Students list from ViewModel ----
//                items(studentList) { student ->
//                    Spacer(modifier = Modifier.height(10.dp))
//
//                    // AttendanceCard must accept isPresent:Boolean and a callback
//                    AttendanceCard(
//                        name = student.name,
//                        rollNo = student.studentId.toString(),
//                        isPresent = (student.status == 1),
//                        onStatusChange = { newStatusInt ->
//                            viewModel.markAttendance(student.studentId, newStatusInt)
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
