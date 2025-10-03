package com.example.attendancesystem.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.attendancesystem.presentation.component.ClassScreen
import com.example.attendancesystem.presentation.screens.classes.ClassViewmodel

@Composable
fun MarkAttendanceScreen1(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ClassViewmodel = hiltViewModel()
) {
    val classList by viewModel.classes.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadAllClasses()
    }

    ClassScreen(
        modifier = modifier,
        title = "Mark Attendance",
        buttonText = "Next",
        classes = classList,
        onButtonClick = { selectedClass ->
            // navigate to Screen 2 with selected classId
            navController.navigate("mark_attendance_2/${selectedClass.classId}")
        },
        onBackClick = {
            navController.popBackStack()   // ✅ back press → previous screen
        }
    )
}
