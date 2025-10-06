//package com.example.attendancesystem.presentation.screens
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import java.time.LocalDate
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.example.attendancesystem.presentation.component.MonthSelector
//import com.example.attendancesystem.presentation.component.PrintCalendar
//import com.example.attendancesystem.presentation.component.SecondBar
//import com.example.attendancesystem.presentation.component.TopBar
//import com.example.attendancesystem.presentation.screens.attendance.AttendanceViewmodel
//import com.example.attendancesystem.presentation.screens.student.StudentViewmodel
//
//@Composable
//fun ViewAttendanceScreen3(
//    navController: NavController,
//    studentId: Int,
//    classId: Int,
//    modifier: Modifier = Modifier,
//    attendanceViewModel: AttendanceViewmodel = hiltViewModel(),
//    studentViewModel: StudentViewmodel = hiltViewModel()
//) {
//    // Attendance data
//    val currentMonth by attendanceViewModel.currentMonth.collectAsState()
//    val days by attendanceViewModel.days.collectAsState()
//
//    // Student data
//    val students by studentViewModel.students.collectAsState()
//    val student = students.find { it.studentId == studentId }
//
//    // Jab screen open ho, student + attendance dono load ho
//    LaunchedEffect(classId, studentId) {
//        studentViewModel.loadStudentsByClass(classId)
//        attendanceViewModel.loadAttendanceForStudent(classId, LocalDate.now())
//    }
//
//    Scaffold(
//        modifier = modifier,
//        topBar = { TopBar(title = "View Attendance") }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ) {
//            student?.let { s ->
//                SecondBar(title = s.name) {}
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Month Selector
//                MonthSelector(
//                    currentMonth = currentMonth,
//                    onPrevious = { attendanceViewModel.previousMonth() },
//                    onNext = { attendanceViewModel.nextMonth() },
//                    onMonthSelected = { month ->
//                        attendanceViewModel.setMonth(month)
//                        attendanceViewModel.loadAttendanceForStudent(classId, month.atDay(1))
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Calendar for this month
//                PrintCalendar(
//                    records = listOf(s), // single student data
//                    days = days
//                )
//            }
//        }
//    }
//}
//
//
