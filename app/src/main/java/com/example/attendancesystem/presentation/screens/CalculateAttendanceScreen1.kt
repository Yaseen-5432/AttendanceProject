//package com.example.attendancesystem.presentation.screens
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.attendancesystem.presentation.component.ClassScreen
//import com.example.attendancesystem.presentation.screens.classes.ClassViewmodel
//import java.time.LocalDate
//
//
//
//@Composable
//fun CalculateAttendanceScreen1(
//    navController: NavController,
//    classId:Int = 0,
//    viewModel: ClassViewmodel = hiltViewModel()
//) {
//    // Collect classes list from ViewModel
//    val classList by viewModel.classes.collectAsState()
//
//    // 🔹 Load classes for current year when screen first opens
//    LaunchedEffect(Unit) {
//        viewModel.setYear(LocalDate.now().year)
//    }
//
//    // UI Screen
//    ClassScreen(
//        title = "Calculate Attendance",
//        buttonText = "Next",
//        classes = classList,
//        onButtonClick = { selectedClass ->
//            // Navigate to student list screen with selected classId
//            navController.navigate("studentList/${selectedClass.classId}")
//        },
//        onBackClick = { navController.popBackStack() } // ✅ back to MyScreen
//
//    )
//}
//
//
//
//@Preview(showSystemUi = true)
//@Composable
//fun PreviewCalculateAttendanceScreen1() {
//    val navController= rememberNavController()
//    CalculateAttendanceScreen1(navController= navController)
//}
