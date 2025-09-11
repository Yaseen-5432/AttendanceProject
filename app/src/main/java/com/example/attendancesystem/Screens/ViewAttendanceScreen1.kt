package com.example.attendancesystem.Screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.attendancesystem.component.ClassScreen


@Composable
fun ViewAttendanceScreen1(modifier: Modifier = Modifier,){
    ClassScreen(modifier = modifier,
        title = "View Attendance",
        buttonText = null
    )
}