package com.example.attendancesystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.attendancesystem.presentation.component.ClassScreen
import com.example.attendancesystem.ui.theme.AttendanceSystemTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttendanceSystemTheme {
                ScafforldScreen()
            }
        }
    }
}


@Composable
fun ScafforldScreen(){
    Scaffold(

    ) { 
            padding ->
        ClassScreen(modifier = Modifier.padding(padding),
             title = "Names"
            )
    }
}
