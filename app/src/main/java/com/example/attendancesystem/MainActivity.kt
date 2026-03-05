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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.attendancesystem.presentation.screens.CalculateAttendanceScreen3
import com.example.attendancesystem.presentation.screens.MainScreen
import com.example.attendancesystem.presentation.screens.MarkAttendanceScreen2
import com.example.attendancesystem.presentation.screens.navigation.AppNavHost


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttendanceSystemTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}

