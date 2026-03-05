package com.example.attendancesystem.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.attendancesystem.presentation.component.AttendanceCalculator
import com.example.attendancesystem.presentation.component.AttendanceCard
import com.example.attendancesystem.presentation.component.DateSelector
import com.example.attendancesystem.presentation.component.MonthSelector
import com.example.attendancesystem.presentation.component.SecondBar
import com.example.attendancesystem.presentation.component.TopBar
import com.example.attendancesystem.presentation.screens.attendance.AttendanceViewmodel
import java.time.LocalDate
import java.time.YearMonth

@SuppressLint("DefaultLocale")
@Composable
fun CalculateAttendanceScreen3(
    navController: NavController,
    studentId: Int,
    modifier: Modifier = Modifier,
    viewModel: AttendanceViewmodel = hiltViewModel()
){
    val student by viewModel.studentName.collectAsState()
    val studentAttendancePercentage by viewModel.studentAttendancePercentage.collectAsState()

    LaunchedEffect(studentId) {
        viewModel.loadStudentName(studentId)
    }
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(title = "Calculate Attendance"){
            navController.popBackStack()
        } }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SecondBar(
                title = student,
                buttonText = null
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item{
                    AttendanceCalculator(String.format("%.2f",studentAttendancePercentage)){startDate, endDate ->

                        viewModel.loadStudentAttendancePercentage(studentId, startDate, endDate)
                    }
                }

            }
        }
    }
}

