package com.example.attendancesystem.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.attendancesystem.component.AttendanceCard
import com.example.attendancesystem.component.DateSelector
import com.example.attendancesystem.component.MonthSelector
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
import com.example.attendancesystem.component.SecondBar
import com.example.attendancesystem.component.TopBar


@Composable
fun MarkAttendanceScreen2(modifier: Modifier = Modifier,
){
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar( title = "Mark Attendance")
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
                buttonText = "Submit"
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

                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .border(2.dp, Color.Black),
                        verticalArrangement = Arrangement.Center,


                        ){
                        MonthSelector()
                        DateSelector()
                    }
                }


//            // List of Attendance Cards
                items(10) { index ->
                    val studentNames = listOf(
                        "Zubair Ahmad", "Ibrahim Khan", "Umair Ashraf",
                        "Hafiz Faizan Sajjid", "Waqas Khizra", "Musa Bhai",
                        "Ibrahim Khan", "Zubair Ahmad", "Hafiz Faizan Sajjid",
                        "Waqas Khizra"
                    )
                    AttendanceCard(name = studentNames[index], rollNo = (index+1).toString()){

                    }

                }
            }
        }
    }
}
