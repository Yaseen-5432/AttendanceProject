package com.example.attendancesystem

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.attendancesystem.component.PrintCalendar
import com.example.attendancesystem.component.ScafforldScreen2
import com.example.attendancesystem.component.MyScreen
import com.example.attendancesystem.component.ScafforldScreen
import com.example.attendancesystem.component.StudentForm
import com.example.attendancesystem.ui.theme.AttendanceSystemTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttendanceSystemTheme {
                ScafforldScreen()
            }
        }
    }
}
