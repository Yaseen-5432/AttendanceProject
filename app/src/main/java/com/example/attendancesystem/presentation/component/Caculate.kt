package com.example.attendancesystem.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate


@Composable
fun AttendanceCalculator(
    attendancePercentage: String?,
    onCalculate: (startDate: LocalDate, endDate: LocalDate) -> Unit
) {
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize(),
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
            DateInputField(
                label = "Start Date:",
                date = startDate,
                onDateChanged = { startDate = it },
            )
            Spacer(modifier = Modifier.height(8.dp))
            DateInputField(
                label = "End Date:",
                date = endDate,
                onDateChanged = { endDate = it }
            )


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (startDate != null && endDate != null) {
                        onCalculate(startDate!!, endDate!!)
                    }
                },
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

            Spacer(modifier = Modifier.height(20.dp))

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
                    ClassPercentage(percentage = attendancePercentage)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
