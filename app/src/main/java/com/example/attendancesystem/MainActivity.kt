package com.example.attendancesystem

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.attendancesystem.Screens.CalculateAttendanceScreen1
import com.example.attendancesystem.Screens.CalculateAttendanceScreen2
import com.example.attendancesystem.Screens.MainScreen
import com.example.attendancesystem.Screens.MarkAttendanceScreen1
import com.example.attendancesystem.Screens.MarkAttendanceScreen2
import com.example.attendancesystem.Screens.ViewAttendanceScreen2
import com.example.attendancesystem.Screens.ViewAttendanceScreen3
import com.example.attendancesystem.Screens.ViewContactsScreen2
import com.example.attendancesystem.Screens.ViewRecordsScreen
import com.example.attendancesystem.component.AttendanceCalculator
import com.example.attendancesystem.component.ClassPercentage
import com.example.attendancesystem.component.InfoField
import com.example.attendancesystem.component.PrintCalendar
import com.example.attendancesystem.component.MyScreen
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


@Composable
fun ScafforldScreen(){
    Scaffold(

    ) { 
            padding ->
        InfoField(modifier = Modifier.padding(padding),
            )
    }
}
