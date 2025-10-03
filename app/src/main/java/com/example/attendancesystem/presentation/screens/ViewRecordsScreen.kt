package com.example.attendancesystem.presentation.screens

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.attendancesystem.presentation.component.CustomButton
import com.example.attendancesystem.presentation.component.SecondBar
import com.example.attendancesystem.presentation.component.TopBar
import com.example.attendancesystem.presentation.screens.classes.ClassViewmodel
import com.example.attendancesystem.presentation.screens.student.StudentViewmodel

@Composable
fun ViewRecordsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    classViewModel: ClassViewmodel = hiltViewModel(),
    studentViewModel: StudentViewmodel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = "View Records",
                onBackClick = { navController.popBackStack() }   // ✅ back button handle
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SecondBar(
                title = "(Select One)",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
            ) {
                // future actions if needed
            }

            Spacer(modifier = Modifier.height(30.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    // ✅ Dynamic buttons for navigation
                    CustomButton(
                        text = "Attendance",
                        onClick = { navController.navigate("view_attendance") },
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    CustomButton(
                        text = "Contacts",
                        onClick = { navController.navigate("view_contacts") },
                    )
                }

                // -----------------------------
                /*
                items(10) { index ->
                    val studentNames = listOf(
                        "Zubair Ahmad", "Ibrahim Khan", "Umair Ashraf",
                        "Hafiz Faizan Sajjid", "Waqas Khizra", "Musa Bhai",
                        "Ibrahim Khan", "Zubair Ahmad", "Hafiz Faizan Sajjid",
                        "Waqas Khizra"
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomButton(
                        text = studentNames[index],
                        roll = (index+1).toString()
                    )
                }
                */
                // -----------------------------
            }
        }
    }
}
