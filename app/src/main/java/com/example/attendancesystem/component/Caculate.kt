package com.example.attendancesystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewAttendanceCalculator() {
    AttendanceCalculator()
}

@Composable
fun AttendanceCalculator() {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var attendancePercentage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp),
    ) {
        Text(
            text = "Select Date to Calculate",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 30.dp)
        )




        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            InputField(
                label = "Start Date:",
                value = startDate,
                onValueChange = { startDate = it },
                isDateIcon = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            InputField(
                label = "End Date:",
                value = endDate,
                onValueChange = { endDate = it },
                isDateIcon = true
            )


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(14.dp))
            ) {
                Text(
                    "Calculate",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            // Result Box
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(
                        MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(10.dp)
                    )

            ) {
                Column {
                    Text(
                        text = "Result:",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp,16.dp,0.dp,0.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // call the percentage function
                    ClassPercentage()
                }
            }
        }
    }
}
