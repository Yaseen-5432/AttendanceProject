package com.example.attendancesystem.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.attendancesystem.presentation.component.Callendar
import com.example.attendancesystem.presentation.component.CustomButton
import com.example.attendancesystem.presentation.component.SecondBar
import com.example.attendancesystem.presentation.component.TopBar


@Composable
fun ViewAttendanceScreen2(modifier: Modifier = Modifier,
){
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar( title = "View Attendance")
        }
    ) {
            padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SecondBar(
                title = "Class 1A",
            ) {

            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {

//            item {
//                {
//
//                }
//            }
                item {

                    Callendar()
                 //   Spacer(modifier = Modifier.height(8.dp))
                }


//            // List of Attendance Cards
            items(10) { index ->
                val studentNames = listOf(
                    "Zubair Ahmad", "Ibrahim Khan", "Umair Ashraf",
                    "Hafiz Faizan Sajjid", "Waqas Khizra", "Musa Bhai",
                    "Ibrahim Khan", "Zubair Ahmad", "Hafiz Faizan Sajjid",
                    "Waqas Khizra"
                )
                Spacer(modifier = Modifier.height(10.dp))
               CustomButton(text = studentNames[index], roll = (index+1).toString())

                }
            }
        }
    }
}
