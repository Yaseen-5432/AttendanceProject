package com.example.attendancesystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp



@Composable
fun Calculate() {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var AttendencePercentage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Select Date to Calculate",fontWeight = FontWeight.Medium)
        // start date input
        OutlinedTextField(
            value = startDate,
            onValueChange = {startDate= it},
            label = {Text ("Start Date")},
            trailingIcon = { Icon(Icons.Filled.DateRange, contentDescription = "Calendar") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        // End Date Input
        OutlinedTextField(
            value = endDate,
            onValueChange = { endDate = it },
            label = { Text("End Date") },
            trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))






    }
}
