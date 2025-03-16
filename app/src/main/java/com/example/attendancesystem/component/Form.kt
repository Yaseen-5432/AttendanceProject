package com.example.attendancesystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StudentForm() {
    var studentName by remember { mutableStateOf("") }
    var fatherName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField(label = "Enter Name:", value = studentName, onValueChange = { studentName = it })
        InputField(label = "Enter Father Name:", value = fatherName, onValueChange = { fatherName = it })
        InputField(label = "Enter Phone:", value = phoneNumber, onValueChange = { phoneNumber = it }, isPhone = true)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { println("Submitted: $studentName, $fatherName, $phoneNumber") },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .width(250.dp)
                .height(45.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Text("Submit", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit, isPhone: Boolean = false) {
    Column {
        Text(text = label, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = if (isPhone) KeyboardOptions(keyboardType = KeyboardType.Phone) else KeyboardOptions.Default,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.inversePrimary, shape = RoundedCornerShape(16.dp)) // Using MaterialTheme color
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewStudentForm() {
    StudentForm()
}
