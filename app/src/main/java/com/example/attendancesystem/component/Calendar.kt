package com.example.attendancesystem.component

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale


@Composable
fun Callendar(){
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(2.dp, Color.Black),
        verticalArrangement = Arrangement.Center

    ) {
        MonthSelector()
        DateSelector()
    }
}

@Composable
fun MonthSelector(){
    val today = LocalDate.now()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp,8.dp,16.dp,0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = today.month.toString()+" "+today.year.toString(),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f),
        )
        IconButton(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(40.dp),
            onClick = {

            }
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Calendar Button",
                modifier = Modifier
                    .size(40.dp),
            )
        }
    }

}

@Composable
fun DateSelector() {
    val daysname = listOf("Sat\n", "Sun\n", "Mon\n", "Tue\n", "Wed\n", "Thu\n", "Fri\n")
    val daysdate = DateFinder()
    val days = List(7){index ->
        daysname[index]+daysdate[index]
    }
    var selectedDate by remember { mutableStateOf(LocalDate.now().dayOfMonth.toString()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp,8.dp,16.dp,8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        days.forEach { day ->
            Text(
                text = day,
                modifier = Modifier
                    .background(if (day.substringAfter("\n") == selectedDate) MaterialTheme.colorScheme.primary else Color.Transparent)
                    .padding(4.dp)
                    .clickable { selectedDate = day.substringAfter("\n") },
                style = MaterialTheme.typography.bodyLarge,
                color = if (day.substringAfter("\n") == selectedDate) Color.Black else Color.DarkGray,
                fontWeight = if (day.substringAfter("\n") == selectedDate) FontWeight.Bold else FontWeight.SemiBold,
            )
        }
    }
}





@Composable
fun DateFinder(): List<String> {
    val today = LocalDate.now()
    var start = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY))

    val week = List(7){index->
        start.plusDays(index.toLong()).dayOfMonth.toString()

    }
    return week

}



