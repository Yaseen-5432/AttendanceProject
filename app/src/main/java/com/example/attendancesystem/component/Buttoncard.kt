package com.example.attendancesystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String? = null,
    icon: ImageVector? = null
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        shape = RoundedCornerShape(30.dp) // 💡 Rounded edges
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.Center,
            horizontalArrangement = if(icon==null) Arrangement.Center else Arrangement.Start
            ,
            modifier = Modifier.fillMaxWidth()

        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(40.dp)

                )
                Spacer(modifier = Modifier.width(8.dp)) // 💡 Space between icon and text

            }
            if (text != null) {
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 26.sp,


                )
            }
        }
    }
}



@Composable
fun MyScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            //verticalArrangement = Arrangement.Center,
           // horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                onClick = { /* Handle button click */ },
                text = "Mark Attendance",
                icon = Icons.Default.CheckCircle
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(
                onClick = { /* Handle Favorite button click*/ },
                text = "View Records",
                icon = Icons.Default.DateRange
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(
                        onClick = { /* Handle Settings button click */ },
                text = "Calculate Attendance",
                icon = Icons.Default.AccountBox
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMyScreen() {
    MyScreen()
}
