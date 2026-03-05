package com.example.attendancesystem.presentation.component

import android.health.connect.datatypes.units.Percentage
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.ui.unit.dp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters



@Composable
fun Callendar(
    currentMonth: YearMonth?,               // ✅ current month from parent
    percentage: String?,                  // ✅ attendance percentage
    onDateSelected: (LocalDate) -> Unit   // ✅ callback for date click
)
{
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(2.dp, Color.Black),
        verticalArrangement = Arrangement.Center,
    ) {
        // ✅ Pass params here
     //   MonthSelector(
          //  currentMonth = currentMonth,
           // onPrevious = onPrevious,
           // onNext = onNext,
          //  onMonthSelected = onMonthSelected
       // )
        MonthSelector(
            currentMonth = currentMonth,
        ){

        }
        ClassPercentage(percentage = percentage)
    }
}

@Composable
fun MonthSelector(
    currentMonth: YearMonth?,
    onMonthSelected: (YearMonth) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp,8.dp,16.dp,2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // ✅ Show month from param instead of LocalDate.now()
        Text(
            text = "${currentMonth?.month} ${currentMonth?.year}",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f),
        )

        // ✅ Example: Next button (replace with previous/next logic as needed)
        IconButton(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(40.dp),
            onClick = { onMonthSelected(YearMonth.now()) } // call parent when clicked
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Calendar Button",
                modifier = Modifier.size(40.dp),
            )
        }
    }
}

@Composable
fun DateSelector(
    selectedDate: LocalDate?, // ✅ Correct param name
    daysdate: List<String>,   // ✅ List of date strings
    onDateSelected: (LocalDate) -> Unit      // ✅ Callback param
) {
    val daysname = listOf("Sat\n", "Sun\n", "Mon\n", "Tue\n", "Wed\n", "Thu\n", "Fri\n")
    val days = List(7){ index ->
        daysname[index] + daysdate[index]
    }

    var localSelected by remember { mutableStateOf(selectedDate ?: LocalDate.now()) }
    // ✅ internal state but starts with param

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp,8.dp,16.dp,8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        days.forEach { day ->
            val dateNumber = day.substringAfter("\n").toInt()
            Text(
                text = day,
                modifier = Modifier
                    .background(if (dateNumber == localSelected.dayOfMonth) MaterialTheme.colorScheme.primary else Color.Transparent)
                    .padding(4.dp)
                    .clickable {
                        val newDate = localSelected.withDayOfMonth(dateNumber)
                        localSelected = newDate
                        onDateSelected(newDate) // ✅ notify parent
                    },
                style = MaterialTheme.typography.bodyLarge,
                color = if (dateNumber == localSelected.dayOfMonth) Color.Black else Color.DarkGray,
                fontWeight = if (dateNumber == localSelected.dayOfMonth) FontWeight.Bold else FontWeight.SemiBold,
            )
        }
    }
}


@Composable
fun DateFinder(): List<String> {
    val today = LocalDate.now()
    val start = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY))
    val week = List(7){ index ->
        start.plusDays(index.toLong()).dayOfMonth.toString()
    }
    return week
}

@Composable
fun PrintCalendar(
    records: List<Any>,
    days: List<LocalDate>
){
    val daysList = MonthList()
    val daysname = listOf("Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp,8.dp,18.dp,2.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        )  {
            daysname.forEach { day ->
                Text(
                    text = day,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }
        for (l in 1..6){
            Row {
                for (i in 1..7) {
                    val index = (l - 1) * 7 + (i - 1)
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .border(1.dp, Color.Black),
                        contentAlignment = Alignment.Center,
                    ){
                        Text(
                            text = daysList[index].dayOfMonth.toString(),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MonthList(): List<LocalDate> {
    val today = LocalDate.of(2025,4,15)
    val firstDayOfMonth = today.withDayOfMonth(1)
    val start = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY))
    val days = List(42) { index ->
        start.plusDays(index.toLong())
    }
    return days
}
