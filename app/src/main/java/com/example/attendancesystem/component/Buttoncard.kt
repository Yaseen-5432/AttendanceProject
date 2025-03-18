package com.example.attendancesystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomButton(
    text: String,
    icon: ImageVector? = null,
    roll: String? = null
) {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal =
                if (icon == null && roll == null) 36.dp else 18.dp
            ),
        contentPadding = PaddingValues(start = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        shape = RoundedCornerShape(30.dp) // 💡 Rounded edges
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if(icon==null && roll == null) Arrangement.Center else Arrangement.Start
            ,
            modifier = Modifier.fillMaxWidth()

        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    tint = MaterialTheme.colorScheme.background,
                    contentDescription = "icon",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(40.dp)
                        .clip(shape = CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(4.dp)


                )
                Spacer(modifier = Modifier.width(8.dp))

            }
            else if (roll != null) {
                RollIcon(roll)
            }
                Text(
                    text = text,
                    style = if(icon==null && roll == null) MaterialTheme.typography.headlineLarge else MaterialTheme.typography.headlineMedium.copy(),
                    modifier = Modifier.padding(vertical = 16.dp)

                )

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
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            CustomButton(
//                text = "Mark Attendance",
//                icon = Icons.Default.CheckCircle
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//
//            CustomButton(
//                text = "View Records",
//                icon = Icons.Default.Search
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//
//            CustomButton(
//                text = "Calculate Attendance",
//                icon = Icons.Default.DateRange
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//
//            CustomButton(
//                text = "Generate Reports",
//                icon = Icons.Default.AddCircle
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//
//            CustomButton(
//                text = "Manage Users",
//                icon = Icons.Default.AccountCircle
//            )
//            Spacer(modifier = Modifier.height(20.dp))
//
            CustomButton(
                text = "Class 1A",
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomButton(
                text = "Class 1B",
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomButton(
                text = "Class 1C",
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomButton(
                text = "Class 2A",
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomButton(
                text = "Class 2B",
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomButton(
                text = "Class 3",
            )
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMyScreen() {
    MyScreen()
}
