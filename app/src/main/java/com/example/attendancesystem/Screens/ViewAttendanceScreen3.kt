package com.example.attendancesystem.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.attendancesystem.component.Callendar
import com.example.attendancesystem.component.CustomButton
import com.example.attendancesystem.component.MonthSelector
import com.example.attendancesystem.component.PrintCalendar
import com.example.attendancesystem.component.SecondBar
import com.example.attendancesystem.component.TopBar


@Composable
fun ViewAttendanceScreen3(modifier: Modifier = Modifier,
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
                title = "Zubair Ahmad",
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

                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .border(2.dp, Color.Black),
                        contentAlignment = Alignment.Center,
                    ){
                        MonthSelector()
                    }
                    PrintCalendar()
                    //   Spacer(modifier = Modifier.height(8.dp))
                }


//            // List of Attendance Cards
              //  items(10) { index ->
//                    val studentNames = listOf(
//                        "Zubair Ahmad", "Ibrahim Khan", "Umair Ashraf",
//                        "Hafiz Faizan Sajjid", "Waqas Khizra", "Musa Bhai",
//                        "Ibrahim Khan", "Zubair Ahmad", "Hafiz Faizan Sajjid",
//                        "Waqas Khizra"
//                    )
//                    Spacer(modifier = Modifier.height(10.dp))
//                    CustomButton(text = studentNames[index], roll = (index+1).toString())
//
//                }
            }
        }
    }
}
