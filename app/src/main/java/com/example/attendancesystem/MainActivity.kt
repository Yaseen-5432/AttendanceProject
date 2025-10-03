package com.example.attendancesystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.attendancesystem.presentation.component.MyScreen
import com.example.attendancesystem.ui.theme.AttendanceSystemTheme
import com.example.attendancesystem.presentation.screens.MarkAttendanceScreen1
import com.example.attendancesystem.presentation.screens.ViewRecordsScreen
import com.example.attendancesystem.presentation.screens.CalculateAttendanceScreen1




import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttendanceSystemTheme {
                AppNavHost()
            }
        }
    }
}

@Composable
fun AppNavHost() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") { MyScreen(navController) }
        composable("mark_attendance_1") { MarkAttendanceScreen1(navController) }

        composable("view_records") { ViewRecordsScreen(navController) }
        composable("calculate_attendance") {
            CalculateAttendanceScreen1(navController = navController)
        }
        composable("reports") { PlaceholderScreen("Reports Screen") }
        composable("manage_users") { PlaceholderScreen("Manage Users Screen") }
    }
}

@Composable
fun PlaceholderScreen(text: String) {
    androidx.compose.material3.Text(text = text)
}
