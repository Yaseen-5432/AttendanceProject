package com.example.attendancesystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.attendancesystem.presentation.component.ClassScreen
import com.example.attendancesystem.presentation.screens.MainScreen
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
fun ScafforldScreen(){
    Scaffold(

    ) { 
            padding ->
        MainScreen(modifier = Modifier.padding(padding))
    }
}

@Composable
fun PlaceholderScreen(text: String) {
    androidx.compose.material3.Text(text = text)
}
