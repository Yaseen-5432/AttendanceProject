package com.example.attendancesystem.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.attendancesystem.presentation.screens.CalculateAttendanceScreen1
import com.example.attendancesystem.presentation.screens.CalculateAttendanceScreen3
import com.example.attendancesystem.presentation.screens.MainScreen
import com.example.attendancesystem.presentation.screens.MarkAttendanceScreen1
import com.example.attendancesystem.presentation.screens.MarkAttendanceScreen2
import com.example.attendancesystem.presentation.screens.StudentScreen
import com.example.attendancesystem.presentation.screens.ViewRecordsScreen


@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AppScreen.Main.route
    ) {
        composable(AppScreen.Main.route) { MainScreen(navController) }
        composable("markAttendanceScreen1") { MarkAttendanceScreen1(navController) }
        composable(AppScreen.CalculateAttendance1.route) { CalculateAttendanceScreen1(navController) }
        composable(
            route = AppScreen.Student.route,
            arguments = listOf(
                navArgument("classId") { type = NavType.IntType },
                navArgument("mode") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val classId = backStackEntry.arguments?.getInt("classId") ?: 0
            val modeArg = backStackEntry.arguments?.getString("mode") ?: StudentScreenMode.CALCULATE.name

            // Safely convert string to enum
            val mode = try {
                StudentScreenMode.valueOf(modeArg.uppercase())
            } catch (e: IllegalArgumentException) {
                StudentScreenMode.CONTACT
            }

            val title = when (mode) {
                StudentScreenMode.CONTACT -> "View Contacts"
                StudentScreenMode.VIEW -> "View Attendance"
                StudentScreenMode.CALCULATE -> "Calculate Attendance"
                else -> "Attendance"
            }

            StudentScreen(navController, classId, title, mode)
        }


        composable("viewRecordsScreen1") { ViewRecordsScreen(navController) }
        composable(
            route = "calculateAttendanceScreen3/{studentId}",
            arguments = listOf(navArgument("studentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: 0
            CalculateAttendanceScreen3(navController = navController, studentId = studentId)
        }
        // Add CalculateAttendanceScreen2 destination with classId argument
    }
}



sealed class AppScreen(val route: String) {
    object Main : AppScreen("main")
    object MarkAttendance1 : AppScreen("markAttendanceScreen1")
    object ViewRecords1 : AppScreen("viewRecordsScreen1")
    object CalculateAttendance1 : AppScreen("CalculateAttendanceScreen1")


    // Screens with arguments
    object MarkAttendance2 : AppScreen("markAttendanceScreen2/{classId}") {
        fun createRoute(classId: Int) = "markAttendanceScreen2/$classId"
    }

    data object Student : AppScreen("studentScreen/{classId}/{mode}") {
        fun createRoute(classId: Int, mode: String) = "studentScreen/$classId/$mode"
    }

    object CalculateAttendance2 : AppScreen("calculateAttendanceScreen2/{classId}") {
        fun createRoute(classId: Int) = "calculateAttendanceScreen2/$classId"
    }

    object CalculateAttendance3 : AppScreen("calculateAttendanceScreen3/{studentId}") {
        fun createRoute(studentId: Int) = "calculateAttendanceScreen3/$studentId"
    }
}

enum class StudentScreenMode { CONTACT, VIEW, CALCULATE }

