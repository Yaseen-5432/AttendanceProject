package com.example.attendancesystem.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.attendancesystem.presentation.component.Callendar
import com.example.attendancesystem.presentation.component.ClassPercentage
import com.example.attendancesystem.presentation.component.CustomButton
import com.example.attendancesystem.presentation.component.InstituteName
import com.example.attendancesystem.presentation.component.SecondBar
import com.example.attendancesystem.presentation.component.TopBar
import com.example.attendancesystem.presentation.screens.navigation.StudentScreenMode
import com.example.attendancesystem.presentation.screens.student.StudentViewmodel
import java.time.LocalDate
import java.time.YearMonth


@SuppressLint("DefaultLocale")
@Composable
fun StudentScreen(
    navController: NavController,
    classId: Int,
    title: String = "Calculate Attendance",
    mode: StudentScreenMode = StudentScreenMode.CALCULATE,
    modifier: Modifier = Modifier,
    viewModel: StudentViewmodel = hiltViewModel()
) {
    val studentList by viewModel.students.collectAsState()
    val sessionStart by viewModel.sessionStartDate.collectAsState()
    val percentage by viewModel.studentAttendancePercentage.collectAsState()
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val className by viewModel.className.collectAsState()
    val currentMonth by viewModel.selectedMonth.collectAsState()

    // Load attendance dynamically on screen load & date change
    LaunchedEffect( classId) {
        if(mode == StudentScreenMode.CALCULATE) {
            viewModel.loadSessionAndAttendance(classId)
        }
        else if(mode == StudentScreenMode.VIEW) {
            viewModel.setSelectedMonth(classId, YearMonth.now())
        }
            viewModel.loadStudentsByClass(classId)
            viewModel.loadClassName(classId)


    }


    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(title = title)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SecondBar(
                title = className,
            ) { /* filter/back action */ }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                if (mode == StudentScreenMode.CALCULATE) {
                    // Class percentage box
                    item {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .border(2.dp, Color.Black),
                            contentAlignment = Alignment.Center,
                        ) {
                            ClassPercentage(percentage = String.format("%.2f", percentage))
                        }
                    }
                }
                else if (mode == StudentScreenMode.VIEW) {

                    item {
                        Callendar(
                            currentMonth = currentMonth,
                            percentage = String.format("%.2f", percentage)
                        ){

                        }
                    }
                }
                else if (mode == StudentScreenMode.CONTACT){
                    item {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .border(2.dp, Color.Black),
                            contentAlignment = Alignment.Center,
                        ) {
                            InstituteName()
                        }
                    }
                }

                // ✅ ViewModel se students ki list
                items(studentList.size) { index ->
                    val student = studentList[index]
                    Spacer(modifier = Modifier.height(10.dp))
                    CustomButton(
                        text = student.name,
                        onClick = {
                            navController.navigate("studentDetail/${student.studentId}")
                        },
                    )


                }


                /*
                items(10) { index ->
                    val studentNames = listOf(
                        "Zubair Ahmad", "Ibrahim Khan", "Umair Ashraf",
                        "Hafiz Faizan Sajjid", "Waqas Khizra", "Musa Bhai",
                        "Ibrahim Khan", "Zubair Ahmad", "Hafiz Faizan Sajjid",
                        "Waqas Khizra"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CustomButton(
                        text = studentNames[index],
                        roll = (index+1).toString()
                    )
                }
                */
            }
        }
    }
}


