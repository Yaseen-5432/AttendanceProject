package com.example.attendancesystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AttendanceCard(modifier: Modifier = Modifier,
                   rollNo: String,
                   name: String,
                   onStatusChange: (Int) -> Unit
){
    var colorState by rememberSaveable { mutableStateOf(0) }

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RollIcon(rollNo)
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                AttendanceButton(colorState,"Present"){
                    colorState = it
                    onStatusChange(it)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AttendanceButton(colorState,"HalfDay"){
                    colorState = it
                    onStatusChange(it)
                }
                AttendanceButton(colorState,"Leave"){
                    colorState = it
                    onStatusChange(it)
                }
                AttendanceButton(colorState,"Absent"){
                    colorState = it
                    onStatusChange(it)
                }
            }
        }
    }
}

@Composable
fun RollIcon(rollNo: String){
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(30.dp)
            .background(MaterialTheme.colorScheme.primary,shape = CircleShape),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = rollNo,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun AttendanceButton(status: Int?,buttonText: String, onStatusChange: (Int) -> Unit ){


    val buttonStatus = when(buttonText){
        "Present" -> 1
        "Absent" -> 2
        "HalfDay" -> 3
        "Leave" -> 4
        else -> 0
    }
    val buttonColor = if (status == buttonStatus) {
        when (buttonStatus) {
            1 -> MaterialTheme.colorScheme.onPrimary
            2 -> MaterialTheme.colorScheme.onSecondary
            3 -> MaterialTheme.colorScheme.onTertiary
            4 -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.background
        }
    } else {
        MaterialTheme.colorScheme.background
    }
    val textcolor = if(buttonColor != MaterialTheme.colorScheme.background) {
        MaterialTheme.colorScheme.background
    }
    else{
        Color.Black
    }
    Button(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .width(100.dp)
            .height(40.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        onClick = {
            val newStatus = when(buttonText){
                "Present" -> 1
                "Absent" -> 2
                "HalfDay" -> 3
                "Leave" -> 4
                else -> 0
            }
            onStatusChange(newStatus)

        }
    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.bodySmall,
           color = textcolor

        )
    }
}