package com.example.attendancesystem.component

import android.R.style
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun InstituteName(){


    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                append("School Name: ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)){
                append("Jamia Riaz Ul Uloom")
            }
        } ,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(20.dp)
    )
}