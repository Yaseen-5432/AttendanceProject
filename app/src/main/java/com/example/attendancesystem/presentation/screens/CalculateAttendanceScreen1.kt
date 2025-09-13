package com.example.attendancesystem.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.attendancesystem.presentation.component.ClassScreen


@Composable
fun CalculateAttendanceScreen1(modifier: Modifier = Modifier,){
    ClassScreen(modifier = modifier,
        title = "Calculate Attendance",
        buttonText = null
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCalculateAttendanceScreen1() {
    CalculateAttendanceScreen1()
}