package com.example.attendancesystem

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.attendancesystem.component.ScafforldScreen
import com.example.attendancesystem.component.ScafforldScreen2
import com.example.attendancesystem.component.TopBar
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
