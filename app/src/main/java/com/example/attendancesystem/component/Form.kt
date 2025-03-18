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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
            .padding(top = 60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField(label = "Enter Name:", value = studentName, onValueChange = { studentName = it })
        Spacer(modifier = Modifier.height(8.dp))
        InputField(label = "Enter Father Name:", value = fatherName, onValueChange = { fatherName = it })
        Spacer(modifier = Modifier.height(8.dp))
        InputField(label = "Enter Phone:", value = phoneNumber, onValueChange = { phoneNumber = it }, isPhone = true)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(14.dp))
        ) {
            Text("Submit",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit, isPhone: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
        .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = label,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
            )
        TextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = if (isPhone) KeyboardOptions(keyboardType = KeyboardType.Phone) else KeyboardOptions.Default,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),  // Rounded corners applied correctly,

            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.secondary, // Background color
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Transparent,

            )
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStudentForm() {
    StudentForm()
}
