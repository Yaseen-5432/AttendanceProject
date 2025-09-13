package com.example.attendancesystem.presentation.component

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


@Composable
fun ClassScreen(modifier: Modifier = Modifier,
                         title: String,
                         buttonText: String? = "Add",
){
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar( title = title)
        }
    ) {
            padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SecondBar(
                title = "Classes-(Select)",
                buttonText = buttonText,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
            ) {

            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {

//            item {
//                {
//
//                }
//            }
//                item {
//
//                    CustomButton(
//                        text = "Class 1A",
//                    )
//                    Spacer(modifier = Modifier.height(10.dp))
//
//                    CustomButton(
//                        text = "Class 1B",
//                    )
//                    Spacer(modifier = Modifier.height(10.dp))
//
//                    CustomButton(
//                        text = "Class 1C",
//                    )
//                    Spacer(modifier = Modifier.height(10.dp))
//
//                    CustomButton(
//                        text = "Class 2A",
//                    )
//                    Spacer(modifier = Modifier.height(10.dp))
//
//                    CustomButton(
//                        text = "Class 2B",
//                    )
//                    Spacer(modifier = Modifier.height(10.dp))
//
//                    CustomButton(
//                        text = "Class 3",
//                    )
//                }

            // List of Attendance Cards
            items(10) { index ->
                val studentNames = listOf(
                    "Zubair Ahmad", "Ibrahim Khan", "Umair Ashraf",
                    "Hafiz Faizan Sajjid", "Waqas Khizra", "Musa Bhai",
                    "Ibrahim Khan", "Zubair Ahmad", "Hafiz Faizan Sajjid",
                    "Waqas Khizra"
                )
                Spacer(modifier = Modifier.height(20.dp))
               CustomButton(text = studentNames[index], roll = (index+1).toString())

                }
            }
        }
    }
}
