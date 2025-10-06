//package com.example.attendancesystem.presentation.screens
//
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.example.attendancesystem.presentation.component.ClassPercentage
//import com.example.attendancesystem.presentation.component.CustomButton
//import com.example.attendancesystem.presentation.component.SecondBar
//import com.example.attendancesystem.presentation.component.TopBar
//import com.example.attendancesystem.presentation.screens.attendance.AttendanceViewmodel
//import java.time.LocalDate
//
//
//@Composable
//fun CalculateAttendanceScreen2(
//    navController: NavController,
//    classId: Int,
//    modifier: Modifier = Modifier,
//    viewModel: AttendanceViewmodel = hiltViewModel()
//) {
//    val studentList by viewModel.attendanceWithStudent.collectAsState()
//    val percentage by viewModel.studentAttendancePercentage.collectAsState()
//    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
//    // Load attendance dynamically on screen load & date change
//        LaunchedEffect(selectedDate, classId) {
//           viewModel.loadAttendanceForStudent(classId, selectedDate)
//            viewModel.loadClassAttendancePercentage(classId, selectedDate)
//        }
//
//    Scaffold(
//        modifier = modifier,
//        topBar = {
//            TopBar(title = "Calculate Attendance")
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ) {
//            SecondBar(
//                title = "Class 1A",
//            ) { /* filter/back action */ }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            LazyColumn(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                // Class percentage box
//                item {
//                    Box(
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .fillMaxWidth()
//                            .border(2.dp, Color.Black),
//                        contentAlignment = Alignment.Center,
//                    ) {
//                        ClassPercentage(percentage = "${percentage.toInt()}%")
//                    }
//                }
//
//                // ✅ ViewModel se students ki list
//                items(studentList.size) { index ->
//                    val student = studentList[index]
//                    Spacer(modifier = Modifier.height(10.dp))
//                    CustomButton(
//                        text = student.name,
//                        onClick = {
//                            navController.navigate("studentDetail/${student.studentId}")
//                        },
//                    )
//
//
//                }
//
//
//                /*
//                items(10) { index ->
//                    val studentNames = listOf(
//                        "Zubair Ahmad", "Ibrahim Khan", "Umair Ashraf",
//                        "Hafiz Faizan Sajjid", "Waqas Khizra", "Musa Bhai",
//                        "Ibrahim Khan", "Zubair Ahmad", "Hafiz Faizan Sajjid",
//                        "Waqas Khizra"
//                    )
//                    Spacer(modifier = Modifier.height(10.dp))
//                    CustomButton(
//                        text = studentNames[index],
//                        roll = (index+1).toString()
//                    )
//                }
//                */
//            }
//        }
//    }
//}
//
//
