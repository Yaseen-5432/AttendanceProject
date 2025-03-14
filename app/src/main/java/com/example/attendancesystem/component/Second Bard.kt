package com.example.attendancesystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondBar(title: String,
              buttonText: String? = null,
              modifier: Modifier = Modifier,
              style: TextStyle = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
              OnSubmit: () -> Unit
){
    Surface(
        modifier = modifier.fillMaxWidth()
            .padding(top = 8.dp),
        color = MaterialTheme.colorScheme.secondary,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical =  8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp)
            ) {
                Text(
                    text = title,
                    style = style,

                )
            }
            if(buttonText != null) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = {
                        OnSubmit()
                    }
                ) {
                    Text(
                        text = buttonText,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }

        }
    }
}