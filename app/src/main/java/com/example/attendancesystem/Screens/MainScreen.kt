package com.example.attendancesystem.Screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.attendancesystem.component.CustomButton
import com.example.attendancesystem.component.MainTopBar
import com.example.attendancesystem.component.MyScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier){
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopBar()
        }
    ) {
            padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

//            item {
//                SecondBar(
//                    title = "Classes-(Select Class)",
//                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
//                ){
//
//                }
//            }
                  item {
                    MyScreen()
//                CustomButton(
//                    text = "Mark Attendance",
//                    icon = Icons.Default.CheckCircle
//                )
//                Spacer(modifier = Modifier.height(10.dp))
//
//                CustomButton(
//                    text = "View Records",
//                    icon = Icons.Default.Search
//                )
//                Spacer(modifier = Modifier.height(10.dp))
//
//                CustomButton(
//                    text = "Calculate Attendance",
//                    icon = Icons.Default.DateRange
//                )
//                Spacer(modifier = Modifier.height(10.dp))
//
//                CustomButton(
//                    text = "Generate Reports",
//                    icon = Icons.Default.AddCircle
//                )
//                Spacer(modifier = Modifier.height(10.dp))
//
//                CustomButton(
//                    text = "Manage Users",
//                    icon = Icons.Default.AccountCircle
//                )
            }
//
//            // List of Attendance Cards
//            items(10) { index ->
//                val studentNames = listOf(
//                    "Zubair Ahmad", "Ibrahim Khan", "Umair Ashraf",
//                    "Hafiz Faizan Sajjid", "Waqas Khizra", "Musa Bhai",
//                    "Ibrahim Khan", "Zubair Ahmad", "Hafiz Faizan Sajjid",
//                    "Waqas Khizra"
//                )
//                Spacer(modifier = Modifier.height(20.dp))
//               CustomButton(text = studentNames[index], roll = (index+1).toString())
//
//                }
        }
    }
}
