package com.example.attendancesystem.presentation.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.attendancesystem.presentation.component.ClassScreen
import com.example.attendancesystem.presentation.screens.classes.ClassViewmodel
import com.example.attendancesystem.presentation.screens.navigation.AppScreen
import com.example.attendancesystem.presentation.screens.navigation.StudentScreenMode
import java.time.LocalDate



@Composable
fun CalculateAttendanceScreen1(
    navController: NavController,
    classId:Int = 0,
    viewModel: ClassViewmodel = hiltViewModel()
) {
    // Collect classes list from ViewModel
    val classList by viewModel.classes.collectAsState()

    // 🔹 Load classes for current year when screen first opens
    LaunchedEffect(Unit) {
        viewModel.loadClasses()
    }

    // UI Screen
    ClassScreen(
        title = "Calculate Attendance",
        buttonText = null,
        classes = classList,
        onButtonClick = { selectedClass ->
            // Navigate to CalculateAttendanceScreen2 with selected classId
            Log.d("calculate", "CalculateAttendanceScreen1: ${selectedClass.classId}")
            navController.navigate(AppScreen.Student.createRoute(selectedClass.classId, StudentScreenMode.CALCULATE.name))

        },
        onBackClick = { navController.popBackStack() } // ✅ back to MyScreen

    )
}



@Preview(showSystemUi = true)
@Composable
fun PreviewCalculateAttendanceScreen1() {
    val navController= rememberNavController()
    CalculateAttendanceScreen1(navController= navController)
}
